package com.example.cardealapplication.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cardealapplication.R
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    lateinit var txtName:EditText
    lateinit var txtEmail:EditText
    lateinit var txtPassword:TextInputEditText
    lateinit var txtPhone:EditText
   lateinit var btnSignUp:Button
    lateinit var txt1:TextView



    val EmailPattern= Pattern.compile( "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    val PasswordPattern= Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")
    val PhonePattern=Pattern.compile("^[6-9]\\d{9}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initView()
    }

    private fun initView() {
        txtName=findViewById(R.id.name)
        txtEmail=findViewById(R.id.emailAddress)
        txtPassword=findViewById(R.id.password)
        txtPhone=findViewById(R.id.phoneNumber)
        btnSignUp=findViewById(R.id.btnSignUp)
        txt1=findViewById(R.id.txt1)

        txt1.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        btnSignUp.setOnClickListener {
            performValidation()
        }
    }

    private fun performValidation() {
        val name=txtName.text.toString()
        val email=txtEmail.text.toString()
        val password=txtPassword.text.toString()
        val phone=txtPhone.text.toString()

        if(name.isEmpty())
        {
            txtName.error="Cannot be Empty"
        } else if(phone.isEmpty()||!isValidPhoneNumber(phone))
        {
            txtPhone.error="Invalid Phone Nuber"
        } else if(email.isEmpty()||!isValidEmail(email))
        {
            txtEmail.error="Invalid Email"
        } else if(password.isEmpty()||!isValidPassword(password))
        {
            txtPassword.error="Minimum eight characters, at least one uppercase letter,\n"+
                    "one lowercase letter, one number and one special character Required"
        } else
        {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
            Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show()

        }

    }

    private fun isValidPhoneNumber(Phone: String): Boolean {
        val matcher:Matcher= PhonePattern.matcher(Phone)
        return matcher.matches()
    }

    private fun isValidPassword(Password: String): Boolean {
        val matcher:Matcher=  PasswordPattern.matcher(Password)
        return matcher.matches()
    }

    private fun isValidEmail(Email: String): Boolean {
        val matcher:Matcher= EmailPattern.matcher(Email)
        return matcher.matches()
    }
}