package com.example.cardealapplication.purchase

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchase2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class PurchaseActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityPurchase2Binding
    private val db = Firebase.firestore

    private var imgList= mutableListOf<CarouselItem>()
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
            val txtState= "Location :-  <b>${data.txtCity}</b>, <b>${data.txtState}</b>"
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
            popUp()
        }
    }

    private fun popUp() {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")

        val dialogBinding = layoutInflater.inflate(R.layout.custom_popup_menu_purchase,null)
        val builder = Dialog(this)

        builder.setContentView(dialogBinding)

        val txtOwnerName:TextView =builder.findViewById(R.id.txtOwnerName)
        val txtOwnerPhone:TextView = builder.findViewById(R.id.txtOwnerPhone)
        val txtOwnerLocation:TextView = builder.findViewById(R.id.txtOwnerLocation)

        val btnCall:Button = builder.findViewById(R.id.btnCall)
        val btnMsg:Button = builder.findViewById(R.id.btnMsg)

        val name :String = "<b>Owner Name</b> :- "+data?.txtName
        val phone : String = "<b>Owner Phone</b> :- "+data?.txtPhone
        val loc = "<b>Owner Location</b> :- ${data?.txtCity}, ${data?.txtState}"

        txtOwnerName.text=Html.fromHtml(name)
        txtOwnerPhone.text=Html.fromHtml(phone)
        txtOwnerLocation.text=Html.fromHtml(loc)

        builder.setCancelable(true)
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        builder.show()

        btnCall.setOnClickListener {
            callSeller()
            builder.dismiss()
        }
        btnMsg.setOnClickListener {
            sendSms()
            builder.dismiss()
        }
    }
    private fun callSeller() {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")
       val dial = Intent(Intent.ACTION_DIAL)
        dial.data =  Uri.parse("tel:"+data?.txtPhone)
        startActivity(dial)
    }
    private fun sendSms() {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("Users").document(uid)
        ref.collection("Users").document(uid)

        var phone: String
        var email: String
        var name: String

        ref.get().addOnSuccessListener {
            if(it!=null){
                phone = it.data?.get("Phone").toString()
                email = it.data?.get("Email").toString()
                name = it.data?.get("Name").toString()

                val url =
                    "https://api.whatsapp.com/send?phone=${data?.txtPhone.toString()}" +
                            "&text=*Details Of Intrested Buyer of Your Car*+"+"\n"+
                            "*Car Name* :- " +data?.txtCarModel.toString()+"\n"+
                            "*Expected Price* :- "+data?.txtCarPrice.toString()+"\n"+
                            "*Name of interested buyer* :- "+name+"\n"+
                            "*Phone Number of interested buyer* :- "+phone+"\n"+
                            "*Email of interested buyer* :- "+email

                val intent = Intent(Intent.ACTION_VIEW).apply {
                    this.data = Uri.parse(url)
                    this.`package` = "com.whatsapp"
                }
                try {
                    startActivity(intent)
                } catch (ex : ActivityNotFoundException){
                    Toast.makeText(this, "Please Install Whatsapp", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}