package com.example.cardealapplication.purchase

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Html
import android.util.Log
import android.view.ViewGroup.LayoutParams
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.adapter.BuyViewPagerAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchaseBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class PurchaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityPurchaseBinding
    private var time : String = ""
    private var date : String = ""
    private var cal = Calendar.getInstance()
    lateinit var btnConfirm : Button
    lateinit var btnTime : ImageButton
    lateinit var btnDate : ImageButton
    lateinit var txtDate : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")
        supportActionBar!!.title = ""+data?.txtCarName
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")
        val adapter = BuyViewPagerAdapter(supportFragmentManager,lifecycle,data)
        binding.viewPager.adapter=adapter
        Glide.with(this).load(data?.img).into(binding.imgCar)

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0 -> {
                    tab.text = "Specifications"
                }
                1-> {
                    tab.text= "Owner Details"
                }
            }
        }.attach()

        binding.btnTestDrive.setOnClickListener {
            popUp()
        }

    }

    private fun popUp() {
        val dialogBinding = layoutInflater.inflate(R.layout.popup_test_drive,null)
        val builder = Dialog(this)
        builder.setContentView(dialogBinding)
        builder.setCancelable(true)
        builder.setCanceledOnTouchOutside(true)
        builder.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
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
                time =  String.format("%d : %d", hourOfDay, minute)
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
           val dateDialog = DatePickerDialog(this@PurchaseActivity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                cal.set(LocalDate.now().year,LocalDate.now().monthValue-1,LocalDate.now().dayOfMonth)
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
                popWindow(txtAddress,txtPhone)
            }
        }
    }
    private fun popWindow(Address: String, Phone: String) {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")
        val message = "<b>Your Test Drive Is Confirmed for ${data?.txtCarName} at </b>  <br><br>"+
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
        builder.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        builder.window?.setBackgroundDrawable(getDrawable(R.drawable.popup_bg))
        builder.show()

        builder.findViewById<Button>(R.id.btnDoneMessage).setOnClickListener {
            sendSMS(data,builder,Phone)
        }

    }
    private fun sendSMS(data: PurchaseDataModel?, builder: Dialog, Phone: String) {
        try {
            val smsManager: SmsManager = this.getSystemService(SmsManager::class.java)
            val message = " Hey ${data?.Name},\n"+"A buyer is interested for your ${data?.txtCarName}. Our person will contact you with further details.\n\nThank You for choosing CarDeal"
            smsManager.sendTextMessage("+91"+data?.Phone, null, message, null, null)

            val msg = "Your Test Drive is Confirmed for ${data?.txtCarName} on " + date +" at " + time
            smsManager.sendTextMessage("+91$Phone", null, msg, null, null)

            builder.dismiss()

        } catch (e: Exception) {
            Log.v("ERROR_MESSAGE",""+e.message.toString())
        }
    }
    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date = sdf.format(cal.time)
        txtDate.text = date

    }
}