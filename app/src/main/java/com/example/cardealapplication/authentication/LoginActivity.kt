package com.example.cardealapplication.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cardealapplication.OptionsActivity
import com.example.cardealapplication.R
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
   lateinit var txtEmail:TextInputEditText
   lateinit  var txtPassword:TextInputEditText
   lateinit var btnLogin:Button
   lateinit var txt1:TextView
   lateinit var txt2:TextView

    val EmailPattern= Pattern.compile( "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    val PasswordPattern= Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {

         txtEmail=findViewById(R.id.emailAddress)
         txtPassword=findViewById(R.id.password)
         btnLogin=findViewById(R.id.btnLogin)
         txt1=findViewById(R.id.txt1_login)
         txt2=findViewById(R.id.txt2)


        txt1.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        txt2.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }

        btnLogin.setOnClickListener {
            performValidation()
        }
    }

    private fun performValidation() {
        val email=txtEmail.text.toString()
        val password=txtPassword.text.toString()
        
        if(email.isEmpty()||!isValidEmail(email)){
            txtEmail.error="Email is not Valid "
        }else if (password.isEmpty()||!isValidPassword(password)){
            txtPassword.error="Minimum eight characters, at least one uppercase letter,\n"+
                    "one lowercase letter, one number and one special character Required"
        }else{
            startActivity(Intent(this,OptionsActivity::class.java))
            finish()
        }
       
    }

    private fun isValidEmail(Email: String): Boolean {
        val matcher:Matcher=EmailPattern.matcher(Email)
     return matcher.matches()
    }

    private fun isValidPassword(Password: String): Boolean {
        val matcher:Matcher= PasswordPattern.matcher(Password)
        return matcher.matches()
    }

}