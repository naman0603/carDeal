package com.example.cardealapplication.myTestDrive

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.TestDriveDataModel
import com.example.cardealapplication.databinding.ActivityMyTestDrive2Binding
import com.example.cardealapplication.myCars.MyCarsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class MyTestDriveActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMyTestDrive2Binding
    private val db = Firebase.firestore
    private var time : String = ""
    private var date : String = ""
    private var cal = Calendar.getInstance()
    private lateinit var btnConfirm : Button
    private lateinit var btnTime : ImageButton
    private lateinit var btnDate : ImageButton
    private lateinit var txtDate : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTestDrive2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<TestDriveDataModel>("Data")
        supportActionBar!!.title = ""+data?.txtCarName

        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<TestDriveDataModel>("Data")

        binding.txtDate.typeface = Typeface.DEFAULT_BOLD
        binding.txtName.typeface = Typeface.DEFAULT_BOLD
        binding.txtTime.typeface = Typeface.DEFAULT_BOLD
        binding.txtAddress.typeface = Typeface.DEFAULT_BOLD

        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Users").document(uid).get().addOnSuccessListener {
            binding.Name.text = it.data?.get("Name").toString()

        }

        Glide.with(this).load(data?.img).into(binding.imgCar)
        binding.Date.text = data?.date.toString()
        binding.Time.text = data?.time.toString()
        binding.Address.text = data?.address.toString()

        binding.btnDelete.setOnClickListener {
            deleteData(data)
        }

        binding.btnReschedule.setOnClickListener {
            popUp(data)
        }

    }
    private fun popUp(data: TestDriveDataModel?) {
        val dialogBinding = layoutInflater.inflate(R.layout.popup_test_drive,null)
        val builder = Dialog(this)
        builder.setContentView(dialogBinding)
        builder.setCancelable(true)
        builder.setCanceledOnTouchOutside(true)
        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        builder.show()

        btnTime = builder.findViewById(R.id.btnTimePicker)
        btnDate = builder.findViewById(R.id.btnCalenderView)
        txtDate = builder.findViewById(R.id.txtDate)

        val mTimePicker: TimePickerDialog
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mCurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                time =  timeFormat.format(calendar.time)
                builder.findViewById<TextView>(R.id.txtTime).text = time
            }
        }, hour, minute, false)

        btnTime.setOnClickListener {

            mTimePicker.show()
        }
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        btnDate.setOnClickListener {
            val dateDialog = DatePickerDialog(this,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                cal.set(LocalDate.now().year, LocalDate.now().monthValue-1, LocalDate.now().dayOfMonth)
                dateDialog.datePicker.minDate = cal.timeInMillis
                dateDialog.show()
            }

        }

        btnConfirm = builder.findViewById(R.id.btnConfirmPopUp)

        btnConfirm.setOnClickListener {

            val txtPhone  = builder.findViewById<EditText>(R.id.txtPhoneNumber).text.toString()

            val txtAddress = builder.findViewById<EditText>(R.id.txtAddressPopUp).text.toString()

            if(txtPhone.isEmpty()){
                builder.findViewById<EditText>(R.id.txtPhoneNumber).error = "Enter Your Number"
            }else if(txtAddress.isEmpty()){
                builder.findViewById<EditText>(R.id.txtAddressPopUp).error = "Enter Address"
            }else{

                builder.dismiss()
                popWindow(txtAddress,txtPhone,data)
            }
        }
    }
    private fun popWindow(Address: String, Phone: String, data: TestDriveDataModel?) {
        val message = "<b>Your Test Drive Is Rescheduled for ${data?.txtCarName} at </b>  <br><br>"+
                "Address : "+ Address+"<br><br>"+
                "Phone : "+ Phone+"<br><br>"+
                "Date : "+ date + "<br><br>"+
                "Time : "+ time

        val dialogBinding = layoutInflater.inflate(R.layout.popup_message_confirm,null)
        val builder = Dialog(this)

        builder.setContentView(dialogBinding)
        builder.setCancelable(true)
        builder.setCanceledOnTouchOutside(true)
        builder.findViewById<TextView>(R.id.txtDone).text = Html.fromHtml(message)
        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        builder.window?.setBackgroundDrawable(getDrawable(R.drawable.popup_bg))
        builder.show()
        val pgbar = builder.findViewById<ProgressBar>(R.id.progressBarMessage)
        pgbar.visibility = View.INVISIBLE
        builder.findViewById<Button>(R.id.btnDoneMessage).setOnClickListener {
            pgbar.visibility = View.VISIBLE
            sendSMS(data,builder,Phone,Address)
        }

    }
    private fun sendSMS(data: TestDriveDataModel?, builder: Dialog, Phone: String, Address: String) {


        db.collection("Test Drive").document(data?.collectionId.toString()).update(
            hashMapOf("address" to Address, "phone" to Phone, "date" to date, "time" to time) as Map<String, Any>
        ).addOnSuccessListener {
            db.collection("Sell Car").document(data?.id.toString()).get().addOnSuccessListener {
                if(it!=null){
                    val smsManager: SmsManager = this.getSystemService(SmsManager::class.java)
                    val message = " Hey ${it.data?.get("Name")},\n"+"A buyer has rescheduled for your ${it.data?.get("txtCarName")}. Our person will contact you with further details.\n\nThank You for choosing CarDeal"
                    smsManager.sendTextMessage("+91"+it.data?.get("Phone"), null, message, null, null)

                    val msg = "Your Test Drive is rescheduled for ${data?.txtCarName} on " + date +" at " + time
                    smsManager.sendTextMessage("+91$Phone", null, msg, null, null)

                    Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, MyTestDriveActivity::class.java))
                    finish()
                    builder.dismiss()
                }
            }.addOnFailureListener {
                Toast.makeText(this , "Please Try Again Later", Toast.LENGTH_SHORT).show()
                Log.e("ERROR_DATABASE",""+it.message)
            }
        }
    }


    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date = sdf.format(cal.time)
        txtDate.text = date
    }


    private fun deleteData(data: TestDriveDataModel?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Are You sure you want to cancel your Test Drive")
        builder.setIcon(R.drawable.ic_caution)

        builder.setPositiveButton("Yes"){ _, _ ->
            Log.v("DELETE_ID",""+data?.collectionId.toString())
            db.collection("Test Drive").document(data?.collectionId.toString()).delete().addOnSuccessListener {
                startActivity(Intent(this, MyTestDriveActivity::class.java))
                finish()
                Toast.makeText(applicationContext, " Successfully Removed ", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, " Error Occurred.\nPlease try again later ", Toast.LENGTH_SHORT).show()
                Log.e("ERROR_DELETE",""+it.message.toString())
            }

        }
        builder.setNegativeButton("No"){ _, _ ->
        }
        builder.setCancelable(true)
        builder.show()
    }
}