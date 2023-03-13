package com.example.cardealapplication.purchase

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchase2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class PurchaseActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityPurchase2Binding
    private var imgList= mutableListOf<CarouselItem>()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchase2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")

        if(data!= null){
            val txtCarModel= "Name :- <b>${data.txtCarModel}</b>"
            val txtCarPrice= "Price :- <b>${data.txtCarPrice}</b>"
            val txtManYear= "Manufacture Year :- <b>${data.txtManYear}</b>"
            val txtKms= "Kilometers Driven :- <b>${data.txtKms}</b>"
            val txtCarVariant= "Car Variant :-<b> ${data.txtCarVariant}</b>"
            val txtState= "State :- <b>${data.txtState}</b>"
            val txtInsurance= "Insurance :- <b>${data.txtInsurance}</b>"
            val txtTransmission= "Transmission :- <b>${data.txtTransmission}</b>"
            val txtOwners= "No. of Owners :-<b> ${data.txtOwners}</b>"
            val txtColor= "Color :-<b> ${data.txtColor}</b>"

            binding.txtCarModel.text= Html.fromHtml(txtCarModel)
            binding.txtCarPrice.text= Html.fromHtml(txtCarPrice)
            binding.txtManYear.text= Html.fromHtml(txtManYear)
            binding.txtKms.text= Html.fromHtml(txtKms)
            binding.txtCarVariant.text= Html.fromHtml(txtCarVariant)
            binding.txtState.text= Html.fromHtml(txtState)
            binding.txtInsurance.text=Html.fromHtml(txtInsurance)
            binding.txtTransmission.text= Html.fromHtml(txtTransmission)
            binding.txtOwners.text= Html.fromHtml(txtOwners)
            binding.txtColor.text=Html.fromHtml(txtColor)
            imgList.add(CarouselItem(imageUrl = data.img))
            binding.img.setData(imgList)
        }

        binding.btnDone.setOnClickListener {
           val builder =  AlertDialog.Builder(this)
            builder.setTitle("Confirm Purchase")
            builder.setMessage("Are you sure you wanna Purchase this car")

            builder.setPositiveButton("Yes") { dialog, _ ->
                startActivity(Intent(this, PurchaseActivity::class.java))
                sendSms()
                dialog.cancel()
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }

            val alert : AlertDialog = builder.create()
            alert.show()
        }

    }

    private fun sendSms() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("Users").document(uid)
        val smsManager: SmsManager = SmsManager.getDefault()
        var phone: String
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")


        ref.get().addOnSuccessListener {
            if (it!=null){
                 phone = it.data?.get("Phone").toString()
                smsManager.sendTextMessage(phone,null,
                "Details Of Car You Wanna Purchase\n"+
                        "Car Name :- "+data?.txtCarModel.toString()+"\n"+
                        "Expected Price :- "+data?.txtCarPrice.toString()+"\n"+
                        "Owner of Car :- "+data?.txtName.toString()+"\n"+
                        "Phone Number Of Owner :- "+data?.txtPhone.toString(),
                    null,null)
            }

        }



        Toast.makeText(this, "Details of Owner has been Messaged", Toast.LENGTH_SHORT).show()
    }
}