package com.example.cardealapplication.purchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.databinding.ActivityPurchase2Binding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PurchaseActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityPurchase2Binding
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchase2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }




}