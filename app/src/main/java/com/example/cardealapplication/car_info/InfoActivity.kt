package com.example.cardealapplication.car_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cardealapplication.adapter.InfoViewPagerAdapter
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.databinding.ActivityInfoBinding
import com.google.android.material.tabs.TabLayoutMediator

class  InfoActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<InfoCarDetailsDataModel>("Data")
        supportActionBar!!.title = ""+data?.txtCarName
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<InfoCarDetailsDataModel>("Data")
        val adapter = InfoViewPagerAdapter(supportFragmentManager,lifecycle,data)
        binding.viewPager.adapter=adapter
        Glide.with(this).load(data?.imgCarView).into(binding.imgCar)


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