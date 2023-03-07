package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.OptionsActivity
import com.example.cardealapplication.databinding.ActivitySell3Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class SellActivity3 : AppCompatActivity() {

    lateinit var binding: ActivitySell3Binding
    private var db = Firebase.firestore
    private val phonePattern= Pattern.compile("^[6-9]\\d{9}\$")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySell3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()

    }

    private fun initView() {

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
        }else if(address.isEmpty()){
            binding.txtAddress.error="Cannot Be Empty"
        }else {
            makeDatabase(name,phone,address,price)
            sendSMS(phone,address)
        }

    }

    private fun sendSMS(phone: String, address: String) {

        val smsManager:SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phone,null,
            "Your Inspection is Confirmed at this address:\n$address",null,null)
        Toast.makeText(this, "Inspection Confirmed", Toast.LENGTH_SHORT).show()

    }

    private fun makeDatabase(name: String, phone: String, address: String, price: String) {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val brand = intent.extras?.getString("brand")
        val model = intent.extras?.getString("model")
        val variant = intent.extras?.getString("variant")
        val year = intent.extras?.getString("year")
        val state = intent.extras?.getString("state")
        val insurance = intent.extras?.getString("insurance")
        val transmission = intent.extras?.getString("transmission")
        val owners = intent.extras?.getString("owners")
        val color = intent.extras?.getString("color")
        val kms = intent.extras?.getString("kms")

        db.collection("Sell Car").document().set(
            hashMapOf(
                "Name" to name,
                "Phone" to phone,
                "Brand" to brand,
                "Model" to model,
                "Variant" to variant,
                "Manufacture Year" to year,
                "State" to state,
                "Insurance" to insurance,
                "Transmission" to transmission,
                "Owners" to owners,
                "Color" to color,
                "Kms" to kms,
                "Address" to address,
                "Expected Price" to price,
                "User Id" to uid
            )
        )

        startActivity(Intent(this, OptionsActivity::class.java))
        finish()
    }
    private fun isValidPhone(Phone: String): Boolean {
        val matcher: Matcher = phonePattern.matcher(Phone)
        return matcher.matches()
    }

}