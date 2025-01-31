package com.example.cardealapplication.sell

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.cardealapplication.MainActivity
import com.example.cardealapplication.databinding.ActivitySell3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.regex.Matcher
import java.util.regex.Pattern

class SellActivity3 : AppCompatActivity() {

    lateinit var binding: ActivitySell3Binding
    private var db = Firebase.firestore
    private lateinit var sr : StorageReference
    private lateinit var fs : FirebaseFirestore
     private var imageViewUrl : String? = null

    private val phonePattern= Pattern.compile("^[6-9]\\d{9}\$")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySell3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()
    }
    private fun initView() {
        binding.progressBar.visibility = View.INVISIBLE
        sr = FirebaseStorage.getInstance().reference.child("Sell Car")
        fs = FirebaseFirestore.getInstance()

        binding.btnDone.setOnClickListener {

            performValidation()
        }
    }
    private fun performValidation() {
        val name=binding.txtName.text.toString()
        val phone=binding.txtPhone.text.toString()
        val address=binding.txtAddress.text.toString()
        val price = binding.txtExpPrice.text.toString()

        if(name.isEmpty()){
            binding.txtName.error="Cannot Be Empty"
        }else if(phone.isEmpty()||!isValidPhone(phone)){
            binding.txtPhone.error="Cannot Be Empty"
        }else if(price.isEmpty()){
            binding.txtExpPrice.error="Cannot Be Empty"
        }else if(address.isEmpty()) {
            binding.txtAddress.error = "Cannot Be Empty"
        }
       else {
            binding.progressBar.visibility = View.VISIBLE
            uploadData(name,phone,address,price)
        }
    }

    private fun uploadData(
        name: String,
        phone: String,
        address: String,
        price: String,

    ) {
        val sp: SharedPreferences = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
        val user = sp.getString("Name","name")
        val imageUri = intent.extras?.getString("ImageUri")

        Log.e("LINK",imageUri.toString())


        sr = sr.child(user.toString()+" "+System.currentTimeMillis())
        sr.putFile(imageUri!!.toUri()).addOnCompleteListener {
            if(it.isSuccessful) {
                sr.downloadUrl.addOnCompleteListener {
                    imageViewUrl = it.result.toString()
                    Log.e("URI",imageViewUrl.toString())

                    makeDatabase(name,phone,address,price)
                }
            }else{
                Toast.makeText(this, ""+it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun makeDatabase(
        name: String,
        phone: String,
        address: String,
        price: String,
    ) {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val txtCarName = intent.extras?.getString("txtCarName")
        val txtManufactureYear = intent.extras?.getString("txtManufactureYear")
        val txtFuelType = intent.extras?.getString("txtFuelType")
        val txtRegisteredState = intent.extras?.getString("txtRegisteredState")
        val txtCity = intent.extras?.getString("txtCity")
        val txtTransmission = intent.extras?.getString("txtTransmission")
        val txtOwners = intent.extras?.getString("txtOwners")
        val txtInsurance = intent.extras?.getString("txtInsurance")
        val txtColor = intent.extras?.getString("txtColor")
        val txtKms = intent.extras?.getString("txtKms")

        db.collection("Sell Car").document().set(
            hashMapOf(
                "Name" to name,
                "Phone" to phone,
                "txtCarName" to txtCarName,
                "Model" to txtManufactureYear,
                "txtFuelType" to txtFuelType,
                "txtRegisteredState" to txtRegisteredState,
                "txtCity" to txtCity,
                "txtTransmission" to txtTransmission,
                "txtOwners" to txtOwners,
                "txtInsurance" to txtInsurance,
                "txtColor" to txtColor,
                "txtKms" to txtKms,
                "Address" to address,
                "Expected Price" to price,
                "User Id" to uid,
                "Image Url" to imageViewUrl.toString()
            )

        )
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun isValidPhone(Phone: String): Boolean {
        val matcher: Matcher = phonePattern.matcher(Phone)
        return matcher.matches()
    }
}