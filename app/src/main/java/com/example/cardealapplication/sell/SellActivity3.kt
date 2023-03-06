package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.OptionsActivity
import com.example.cardealapplication.databinding.ActivitySell3Binding
import java.util.regex.Matcher
import java.util.regex.Pattern

class SellActivity3 : AppCompatActivity() {

    lateinit var binding: ActivitySell3Binding

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

        if(name.isEmpty()){
            binding.txtName.error="Cannot Be Empty"
        }else if(phone.isEmpty()||!isValidPhone(phone)){
            binding.txtPhone.error="Cannot Be Empty"
        }else if(address.isEmpty()){
            binding.txtAddress.error="Cannot Be Empty"
        }else {
            startActivity(Intent(this, OptionsActivity::class.java))
            finish()
            sendSMS()
        }

    }

    private fun sendSMS() {
        val phone=binding.txtPhone.text.toString()
        val address=binding.txtAddress.text.toString()


        val smsManager:SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phone,null,
            "Your Inspection is Confirmed at this address:\n$address",null,null)
        Toast.makeText(this, "Inspection Confirmed", Toast.LENGTH_SHORT).show()
    }

    private fun isValidPhone(Phone: String): Boolean {
        val matcher: Matcher = phonePattern.matcher(Phone)
        return matcher.matches()
    }

}