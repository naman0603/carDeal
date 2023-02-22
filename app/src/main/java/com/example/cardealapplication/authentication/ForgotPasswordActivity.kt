package com.example.cardealapplication.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivityForgotPasswordBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityForgotPasswordBinding

    var email : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth

        initView()
    }

    private fun initView() {



        binding.btnSubmit.setOnClickListener {
            email = binding.emailAddress.text.toString().trim()
            Log.d(TAG,"Successfull "+ email)
            auth.sendPasswordResetEmail(email).addOnSuccessListener {
                startActivity(Intent(this,LoginActivity::class.java))
                Toast.makeText(this, "Check Your Email", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()

            }
        }

    }


}