package com.example.cardealapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.databinding.ActivityAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountActivity : AppCompatActivity() {

    lateinit var binding: ActivityAccountBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
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

        binding.txtName.text=it.data?.get("Name").toString()
        binding.txtEmail.text=it.data?.get("Email").toString()
        binding.txtPhone.text= it.data?.get("Phone").toString()

    }

}