package com.example.cardealapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivitySignUpBinding
    lateinit var uid :String
    val db = Firebase.firestore


    val EmailPattern= Pattern.compile( "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    val PasswordPattern= Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")
    val PhonePattern=Pattern.compile("^[6-9]\\d{9}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        initView()
    }

    private fun initView() {

        binding.txt1.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

       binding.btnSignUp.setOnClickListener {
            performValidation()
        }
    }

    private fun performValidation() {
        val name=binding.name.text.toString()
        val email=binding.emailAddress.text.toString()
        val password=binding.password.text.toString()
        val phone=binding.phoneNumber.text.toString()

        if(name.isEmpty())
        {
            binding.name.error="Cannot be Empty"
        } else if(phone.isEmpty()||!isValidPhoneNumber(phone))
        {
            binding.phoneNumber.error="Invalid Phone Nuber"
        } else if(email.isEmpty()||!isValidEmail(email))
        {
            binding.emailAddress.error="Invalid Email"
        } else if(password.isEmpty()||!isValidPassword(password))
        {
            binding.emailAddress.error="Minimum eight characters, at least one uppercase letter,\n"+
                    "one lowercase letter, one number and one special character Required"
        } else
        {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                if(it.isSuccessful)
                {
                   uid= auth.uid.toString()
                    addData(name,email,phone,uid)
                    auth.currentUser!!.sendEmailVerification().addOnCompleteListener {

                            auth.signOut()
                            startActivity(Intent(this,LoginActivity::class.java))
                            finish()
                            Toast.makeText(this, "SignUp Successful\n" +
                                    "Verification Mail has been Sent", Toast.LENGTH_SHORT).show()


                    }.addOnFailureListener{
                        Toast.makeText(this, " Error Occured ", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "SignUp Failed", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }

    private fun addData(Name: String, Email: String, Phone: String, Uid: String) {


        db.collection("Users").document(Uid).set(

            hashMapOf(
                "Name" to Name,
                "Email" to Email,
                "Phone" to Phone,
                "User Id" to Uid
            )
        )
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