package com.example.cardealapplication.purchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cardealapplication.adapter.BuyViewPagerAdapter
import com.example.cardealapplication.dataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchaseBinding
import com.google.android.material.tabs.TabLayoutMediator
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
        Glide.with(this).load(data?.img).into(binding.imgCar)

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