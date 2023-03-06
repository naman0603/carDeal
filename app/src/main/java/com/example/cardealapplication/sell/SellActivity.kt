package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySellBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SellActivity : AppCompatActivity() {

    lateinit var binding: ActivitySellBinding
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()


    }

    private fun initView() {

        brandItemView()
        variantItemView()
        yearItemView()
        stateItemView()

        binding.btnContinue.setOnClickListener {
            performValidation()
        }

    }

    private fun performValidation() {
        val brand = binding.txtBrand.text.toString()
        val model = binding.txtModel.text.toString()
        val variant = binding.txtVariant.text.toString()
        val year = binding.txtYear.text.toString()
        val state = binding.txtState.text.toString()

        if (brand.isEmpty()) {
            binding.txtBrand.error = "Cannot Be Empty"
        } else if (model.isEmpty()) {
            binding.txtModel.error = "Cannot Be Empty"
        } else if (variant.isEmpty()) {
            binding.txtVariant.error = "Cannot Be Empty"
        } else if (year.isEmpty()) {
            binding.txtYear.error = "Cannot Be Empty"
        } else if (state.isEmpty()) {
            binding.txtState.error = "Cannot Be Empty"
        } else {
            addData(brand,model,variant,year,state)
            startActivity(Intent(this, SellActivity2::class.java))
        }
    }

    private fun addData(brand: String, model: String, variant: String, year: String, state: String) {
        db.collection("Sell Car").document().set(
            hashMapOf(
                "Brand" to brand,
                "Model" to model,
                "Variant" to variant,
                "Manufacture Year" to year,
                "State" to state
            )
        )
    }

    private fun yearItemView() {
        val yearItems = listOf(
            "2000","2001","2002","2003","2004","2005",
            "2006","2007","2008","2009","2010",
            "2011","2012","2013","2014","2015",
            "2016","2017","2018","2019","2020",
            "2021","2022","2023")
        val adapter = ArrayAdapter(this, R.layout.list_item, yearItems.reversed())
        binding.txtYear.setAdapter(adapter)
    }

    private fun stateItemView() {
        val stateItems = listOf(
            "Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh",
            "Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir",
            "Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra",
            "Manipur","Meghalaya","Mizoram","Nagaland","Odisha",
            "Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana",
            "Tripura","Uttar Pradesh","Uttarakhand","West Bengal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtState.setAdapter(adapter)
    }

    private fun variantItemView() {
        val variantItems = listOf("Petrol","Diesel","CNG","Electric")
        val adapter = ArrayAdapter(this, R.layout.list_item, variantItems)
        binding.txtVariant.setAdapter(adapter)
    }


    private fun brandItemView() {
        val brandItems =  listOf("Maruti","Hyundai","Tata")
        val adapter = ArrayAdapter(this, R.layout.list_item,brandItems )
        binding.txtBrand.setAdapter(adapter)

        binding.txtBrand.onItemClickListener=
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(this, ""+binding.txtBrand.text, Toast.LENGTH_SHORT).show()

                when (position) {
                    0 -> {
                        marutiItemView()
                    }
                    1 -> {
                        hyundaiItemView()
                    }
                    2 -> {
                        tataItemView()
                    }
                }

            }

    }

    private fun tataItemView() {
        val modelItems = listOf("Altroz", "Punch", "Nexon", "Harrier","Safari")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }

    private fun hyundaiItemView() {
        val modelItems = listOf("Creta", "Venue", "i10", "i20","Verna")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }

    private fun marutiItemView() {
        val modelItems = listOf("Ertiga", "Wagon R", "Grand Vitara", "Swift","Brezza")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }
}