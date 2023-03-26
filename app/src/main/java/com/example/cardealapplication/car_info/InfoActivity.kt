package com.example.cardealapplication.car_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cardealapplication.R
import com.example.cardealapplication.adapter.InfoViewPagerAdapter
import com.example.cardealapplication.databinding.ActivityInfoBinding
import com.example.cardealapplication.fragments.InfoDetailsFragment
import com.example.cardealapplication.fragments.InfoSpecificationFragment
import com.google.android.material.tabs.TabLayout
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
        val adapter = InfoViewPagerAdapter(supportFragmentManager)

       adapter.addFragment(InfoDetailsFragment(),"Details")
       adapter.addFragment(InfoSpecificationFragment(),"Specs")

        binding.viewPager.adapter=adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }
}