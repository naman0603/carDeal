package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.OptionsActivity
import com.example.cardealapplication.R
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Matcher
import java.util.regex.Pattern

class SellActivity3 : AppCompatActivity() {

    lateinit var txtName:TextInputEditText
    lateinit var txtAddress:TextInputEditText
    lateinit var txtPhone:TextInputEditText

    lateinit var btnDone:Button

    val PhonePattern= Pattern.compile("^[6-9]\\d{9}\$")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_3)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()

    }

    private fun initView() {

        txtName=findViewById(R.id.txtName)
        txtPhone=findViewById(R.id.txtPhone)
        txtAddress=findViewById(R.id.txtAddress)

        btnDone=findViewById(R.id.btnDone)

        btnDone.setOnClickListener {
            performValidation()
        }
    }

    private fun performValidation() {

        val name=txtName.text.toString()
        val phone=txtPhone.text.toString()
        val address=txtAddress.text.toString()

        if(name.isEmpty()){
            txtName.error="Cannot Be Empty"
        }else if(phone.isEmpty()||!isValidPhone(phone)){
            txtPhone.error="Cannot Be Empty"
        }else if(address.isEmpty()){
            txtAddress.error="Cannot Be Empty"
        }else {
            startActivity(Intent(this, OptionsActivity::class.java))
            sendSMS()
        }

    }

    private fun sendSMS() {
        val phone=txtPhone.text.toString()
        val address=txtAddress.text.toString()


        val smsManager:SmsManager
        smsManager=SmsManager.getDefault()
        smsManager.sendTextMessage(phone,null,"Your Inspection is Confirmed at this address:\n"+address,null,null)
        Toast.makeText(this, "Inspection Confirmed", Toast.LENGTH_SHORT).show()
    }

    private fun isValidPhone(Phone: String): Boolean {
        val matcher: Matcher = PhonePattern.matcher(Phone)
        return matcher.matches()
    }

}