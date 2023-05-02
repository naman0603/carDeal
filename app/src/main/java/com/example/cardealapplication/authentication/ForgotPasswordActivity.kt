package com.example.cardealapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityForgotPasswordBinding

    private var email : String = ""
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
            auth.sendPasswordResetEmail(email).addOnSuccessListener {
                startActivity(Intent(this,LoginActivity::class.java))
                Toast.makeText(this, "Check Your Email", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "No User with this Email Id", Toast.LENGTH_SHORT).show()
            }
        }
    }


}