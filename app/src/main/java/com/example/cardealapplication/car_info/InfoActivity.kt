package com.example.cardealapplication.car_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivityInfoBinding
import com.example.cardealapplication.sell.SellActivity2

class InfoActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()

    }

    private fun initView() {

        brandItemView()
        modelItemView()
        variantItemView()

        binding.btnSubmit.setOnClickListener {
            performValidation()
        }

    }

    private fun performValidation() {
        val Brand=binding.txtBrand.text.toString()
        val Model=binding.txtModel.text.toString()
        val Variant=binding.txtVariant.text.toString()

        if(Brand.isEmpty())
        {
            binding.txtBrand.error="Cannot Be Empty"
        }else if(Model.isEmpty())
        {
            binding.txtModel.error="Cannot Be Empty"
        }else if(Variant.isEmpty())
        {
            binding.txtVariant.error="Cannot Be Empty"
        }else
        {
            startActivity(Intent(this, InfoActivity2::class.java))
        }
    }

    private fun brandItemView() {
        val brandItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,brandItems)
        binding.txtBrand.setAdapter(adapter)

    }

    private fun modelItemView() {
        val modelItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,modelItems)
        binding.txtModel.setAdapter(adapter)
    }

    private fun variantItemView() {
        val variantItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,variantItems)
        binding.txtVariant.setAdapter(adapter)

    }
}