package com.example.cardealapplication.sell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.cardealapplication.R

class SellActivity : AppCompatActivity() {

    lateinit var txtYear:AutoCompleteTextView
    lateinit var txtState: AutoCompleteTextView
    lateinit var txtModel: AutoCompleteTextView
    lateinit var txtBrand: AutoCompleteTextView
    lateinit var txtVariant: AutoCompleteTextView

    lateinit var btnContinue:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()
    }
    private fun initView() {

        txtYear = findViewById(R.id.txtYear)
        txtState = findViewById(R.id.txtState)
        txtModel= findViewById(R.id.txtModel)
        txtBrand= findViewById(R.id.txtBrand)
        txtVariant= findViewById(R.id.txtVariant)

        brandItemView()
        modelItemView()
        variantItemView()
        yearItemView()
        stateItemView()

        btnContinue=findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            performValidation()
        }

    }

    private fun performValidation() {
        val Brand=txtBrand.text.toString()
        val Model=txtModel.text.toString()
        val Variant=txtVariant.text.toString()
        val Year=txtYear.text.toString()
        val State=txtState.text.toString()

        if(Brand.isEmpty())
        {
            txtBrand.error="Cannot Be Empty"
        }else if(Model.isEmpty())
        {
            txtModel.error="Cannot Be Empty"
        }else if(Variant.isEmpty())
        {
            txtVariant.error="Cannot Be Empty"
        }else if(Year.isEmpty())
        {
            txtYear.error="Cannot Be Empty"
        }else if(State.isEmpty())
        {
            txtState.error="Cannot Be Empty"
        }else
        {
            startActivity(Intent(this,SellActivity2::class.java))
        }

    }


    private fun yearItemView() {
        val yearItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,yearItems)
        txtYear.setAdapter(adapter)
    }

    private fun stateItemView() {
        val stateItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,stateItems)
        txtState.setAdapter(adapter)
    }

    private fun variantItemView() {
        val variantItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,variantItems)
        txtVariant.setAdapter(adapter)
    }

    private fun modelItemView() {
        val modelItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,modelItems)
        txtModel.setAdapter(adapter)
    }

    private fun brandItemView() {
        val brandItems = listOf("1","2","3","4")
        val adapter = ArrayAdapter(this,R.layout.list_item,brandItems)
        txtBrand.setAdapter(adapter)

    }
}