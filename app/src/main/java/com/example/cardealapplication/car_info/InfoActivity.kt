package com.example.cardealapplication.car_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.cardealapplication.R
import com.example.cardealapplication.sell.SellActivity2

class InfoActivity : AppCompatActivity() {

    lateinit var txtModel: AutoCompleteTextView
    lateinit var txtBrand: AutoCompleteTextView
    lateinit var txtVariant: AutoCompleteTextView

    lateinit var btnSubmit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()

    }

    private fun initView() {
        txtModel= findViewById(R.id.txtModel)
        txtBrand= findViewById(R.id.txtBrand)
        txtVariant= findViewById(R.id.txtVariant)

        brandItemView()
        modelItemView()
        variantItemView()

        btnSubmit=findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            performValidation()
        }

    }

    private fun performValidation() {
        val Brand=txtBrand.text.toString()
        val Model=txtModel.text.toString()
        val Variant=txtVariant.text.toString()

        if(Brand.isEmpty())
        {
            txtBrand.error="Cannot Be Empty"
        }else if(Model.isEmpty())
        {
            txtModel.error="Cannot Be Empty"
        }else if(Variant.isEmpty())
        {
            txtVariant.error="Cannot Be Empty"
        }else
        {
            startActivity(Intent(this, InfoActivity2::class.java))
        }
    }

    private fun brandItemView() {
        val brandItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,brandItems)
        txtBrand.setAdapter(adapter)

    }

    private fun modelItemView() {
        val modelItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,modelItems)
        txtModel.setAdapter(adapter)
    }

    private fun variantItemView() {
        val variantItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,variantItems)
        txtVariant.setAdapter(adapter)

    }
}