package com.example.cardealapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cardealapplication.databinding.ActivityChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class ChangePasswordActivity : AppCompatActivity() {
    lateinit var binding : ActivityChangePasswordBinding
    private val passwordPattern= Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        supportActionBar!!.title = "Change Password"
        setContentView(binding.root)
        auth = Firebase.auth
        initView()
    }

    private fun initView() {

        binding.btnSubmit.setOnClickListener {
            val oldPass = binding.oldPassword.text.toString()
            val newPass = binding.newPassword.text.toString()
            val conNewPass = binding.confirmPassword.text.toString()

            val sp: SharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sp.edit()

            val pass = sp.getString("Password","")

            if(oldPass.isEmpty()|| oldPass != pass)
            {
                binding.oldPassword.error = "Incorrect Password"
            }else if (newPass.isEmpty()|| !isValidPassword(newPass))
            {
                binding.newPassword.error = "Minimum eight characters, at least one uppercase letter,\n"+
                        "one lowercase letter, one number and one special character Required"
            }else if (newPass!=conNewPass)
            {
                binding.confirmPassword.error = "Password Doesn't Match"
            }else
            {
                auth.currentUser?.updatePassword(newPass)?.addOnCompleteListener {
                    startActivity(Intent(this,MainActivity::class.java))
                    Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show()
                    editor.putString("Password",newPass)
                    editor.apply()
                }
            }
        }
    }
    private fun isValidPassword(Password: String): Boolean {
        val matcher: Matcher =  passwordPattern.matcher(Password)
        return matcher.matches()
    }

}