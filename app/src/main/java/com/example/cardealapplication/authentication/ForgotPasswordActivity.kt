package com.example.cardealapplication.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cardealapplication.R
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Matcher
import java.util.regex.Pattern

class ForgotPasswordActivity : AppCompatActivity() {
   lateinit var txtnewPass:TextInputEditText
    lateinit var txtconNewPass:TextInputEditText
    lateinit var btnSubmit:Button

    val PasswordPattern= Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        initView()
    }

    private fun initView() {
         txtnewPass=findViewById(R.id.new_password)
         txtconNewPass=findViewById(R.id.confirm_new_password)
         btnSubmit=findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            performValidation()
        }
    }

    private fun performValidation() {

        val newPassword=txtnewPass.text.toString()
        val confirmNewPassword=txtconNewPass.text.toString()

        if(newPassword.isEmpty()||!isValidPassword(newPassword)){
             txtnewPass.error="Cannot Be Empty"

        }else if (confirmNewPassword.isEmpty())
        {
            txtconNewPass.error="Cannot be Empty"
        }else if (confirmNewPassword!=newPassword)
        {
            txtconNewPass.error="Password need to be Same"
        }else
        {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()

            Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidPassword(Password: String): Boolean {
        val matcher:Matcher=PasswordPattern.matcher(Password)
        return matcher.matches()
    }
}