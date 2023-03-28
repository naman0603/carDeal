package com.example.cardealapplication.car_info

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.adapter.InfoViewPagerAdapter
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.databinding.ActivityInfoBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityInfoBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<InfoCarDetailsDataModel>("Data")
        val adapter = InfoViewPagerAdapter(supportFragmentManager,lifecycle,data)
        binding.viewPager.adapter=adapter


        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0 -> {
                    tab.text = "Details"
                }
                1-> {
                    tab.text= "Specifications"
                }
            }
        }.attach()
    }
}