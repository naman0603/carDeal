package com.example.cardealapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.databinding.ActivityAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class AccountActivity : AppCompatActivity() {
    lateinit var binding: ActivityAccountBinding
    private val db = Firebase.firestore
    private lateinit var uid :String
    private val emailPattern= Pattern.compile( "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
    private val phonePattern= Pattern.compile("^[6-9]\\d{9}\$")
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        initView()
        onClick()
    }

    @SuppressLint("SetTextI18n")
    private fun onClick() {
        binding.btnEditProfile.setOnClickListener {
            binding.txtEmail.setText("")
            binding.txtPhone.setText("")
            binding.txtName.setText("")
            binding.btnEditProfile.text = "Save your data"
            binding.btnEditProfile.setTextColor(Color.WHITE)
            binding.btnEditProfile.setOnClickListener {
                updateData()
            }
        }
    }

    private fun updateData() {
        val name=binding.txtName.text.toString()
        val email=binding.txtEmail.text.toString()
        val phone=binding.txtPhone.text.toString()

        if(name.isEmpty())
        {
            binding.txtName.error="Cannot be Empty"
        } else if(phone.isEmpty()||!isValidPhoneNumber(phone))
        {
            binding.txtPhone.error="Invalid Phone Number"
        } else if(email.isEmpty()||!isValidEmail(email))
        {
            binding.txtEmail.error="Invalid Email"
        }else{
             uid = auth.currentUser!!.uid
            val user = auth.currentUser

            Toast.makeText(this, ""+email, Toast.LENGTH_SHORT).show()

            user?.updateEmail(email)?.addOnCompleteListener {
                if(it.isSuccessful) {
                    db.collection("Users").document(uid).update(
                        mapOf(
                            "Name" to name,
                            "Email" to email,
                            "Phone" to phone
                        )
                    ).addOnCompleteListener {
                        val sp: SharedPreferences = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
                        val editor : SharedPreferences.Editor = sp.edit()
                        editor.putString("Name",name)
                        editor.putString("Email",email)
                        editor.putString("Phone",phone)
                        editor.apply()
                        startActivity(Intent(this,AccountActivity::class.java))
                        finish()
                        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "Please Log Out and Login again for reverification", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
    private fun isValidPhoneNumber(Phone: String): Boolean {
        val matcher: Matcher = phonePattern.matcher(Phone)
        return matcher.matches()
    }
    private fun isValidEmail(Email: String): Boolean {
        val matcher: Matcher = emailPattern.matcher(Email)
        return matcher.matches()
    }

    private fun initView() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("Users").document(uid)

        ref.get().addOnSuccessListener {
        if (it!=null){
            showData(it)
        }   else{
            Toast.makeText(this, " Not Successful", Toast.LENGTH_SHORT).show()
        }
        }
    }
    private fun showData(it: DocumentSnapshot) {
        binding.txtName.setText(it.data?.get("Name").toString())
        binding.txtEmail.setText(it.data?.get("Email").toString())
        binding.txtPhone.setText(it.data?.get("Phone").toString())
    }
}