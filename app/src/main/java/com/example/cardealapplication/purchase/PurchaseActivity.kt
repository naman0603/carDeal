package com.example.cardealapplication.purchase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardealapplication.R
import com.example.cardealapplication.adapter.BuyViewPagerAdapter
import com.example.cardealapplication.adapter.InfoViewPagerAdapter
import com.example.cardealapplication.dataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchaseBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore.getInstance
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PurchaseActivity : AppCompatActivity() {
    private lateinit var dataAdapter: PurchaseDataAdapter
    lateinit var binding: ActivityPurchaseBinding
    private var model=java.util.ArrayList<PurchaseDataModel>()
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<PurchaseDataModel>("Data")
        val adapter = BuyViewPagerAdapter(supportFragmentManager,lifecycle,data)
        binding.viewPager.adapter=adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0 -> {
                    tab.text = "Specifications"
                }
                1-> {
                    tab.text= "Owner Details"
                }
            }
        }.attach()
    }

}