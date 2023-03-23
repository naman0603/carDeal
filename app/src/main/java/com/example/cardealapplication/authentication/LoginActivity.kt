package com.example.cardealapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.MainActivity
import com.example.cardealapplication.OptionsActivity
import com.example.cardealapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
   private lateinit var auth: FirebaseAuth
   lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        initView()
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null && currentUser.isEmailVerified){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    private fun initView() {
        binding.txt1Login.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        binding.txt2.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            performValidation()
        }
    }
    private fun performValidation() {
        val email=binding.emailAddress.text.toString()
        val password=binding.password.text.toString()
        
        if(email.isEmpty()||!isValidEmail(email)){
            binding.emailAddress.error="Cannot Be Empty "
        }else if (password.isEmpty()||!isValidPassword(password)){
            binding.password.error="Cannot Be Empty"
        }else{
           auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
               if(it.isSuccessful ){
                   if(auth.currentUser!!.isEmailVerified){
                       startActivity(Intent(this,OptionsActivity::class.java))
                       finish()
                   }else{
                       auth.currentUser!!.sendEmailVerification().addOnCompleteListener {
                           Toast.makeText(this, " Mail has been sent\n " +
                                   " Please Verify Your mail ", Toast.LENGTH_SHORT).show()
                       }
                   }
               }else{
                   Toast.makeText(baseContext, "Incorrect Email or Password", Toast.LENGTH_SHORT).show()
               }
           }
        }
    }
    private fun isValidEmail(Email: String): Boolean {
    /*    val matcher:Matcher=EmailPattern.matcher(Email)
     return matcher.matches()*/
        return true
    }
    private fun isValidPassword(Password: String): Boolean {
       /* val matcher:Matcher= PasswordPattern.matcher(Password)
        return matcher.matches()*/
        return true
    }

}