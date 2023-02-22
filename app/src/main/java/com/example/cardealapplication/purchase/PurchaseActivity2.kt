package com.example.cardealapplication.purchase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivityPurchase2Binding

class PurchaseActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityPurchase2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchase2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}