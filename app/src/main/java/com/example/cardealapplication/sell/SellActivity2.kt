package com.example.cardealapplication.sell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.cardealapplication.R
import com.google.android.material.textfield.TextInputEditText

class SellActivity2 : AppCompatActivity() {
    lateinit var txtType: AutoCompleteTextView
    lateinit var txtTransmission: AutoCompleteTextView
    lateinit var txtOwners: AutoCompleteTextView
    lateinit var txtColor: TextInputEditText
    lateinit var txtKms:  TextInputEditText

    lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_2)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()

    }
    private fun initView() {
        txtType = findViewById(R.id.txtType)
        txtTransmission = findViewById(R.id.txtTransmission)
        txtOwners= findViewById(R.id.txtOwners)
        txtColor= findViewById(R.id.txtColor)
        txtKms= findViewById(R.id.txtKms)
        btnContinue = findViewById(R.id.btnContinue)

        typeItemView()
        transmissionItemView()
        ownersItemView()

        btnContinue.setOnClickListener {
            performValidation()
        }

    }


    private fun ownersItemView() {

        val ownersItem = listOf("1","2","3","4")
        val adapter=ArrayAdapter(this,R.layout.list_item,ownersItem)
        txtOwners.setAdapter(adapter)
    }

    private fun transmissionItemView() {

        val transmissionItems = listOf("1","2","3","4")
        val adapter=ArrayAdapter(this,R.layout.list_item,transmissionItems)
        txtTransmission.setAdapter(adapter)

    }

    private fun typeItemView() {

        val typeItem = listOf("1","2","3","4")
        val adapter=ArrayAdapter(this,R.layout.list_item,typeItem)
        txtType.setAdapter(adapter)


    }


    private fun performValidation() {

        val type =txtType.text.toString()
        val transmission = txtTransmission.text.toString()
        val owners = txtOwners.text.toString()
        val color = txtColor.text.toString()
        val kms = txtKms.text.toString()

        if(type.isEmpty())
        {
            txtType.error="Cannot Be Empty"
        }else if(transmission.isEmpty())
        {
            txtTransmission.error="Cannot Be Empty"
        }else if(owners.isEmpty())
        {
            txtOwners.error="Cannot Be Empty"
        }else if(color.isEmpty())
        {
            txtColor.error="Cannot Be Empty"
        }else if(kms.isEmpty())
        {
            txtKms.error="Cannot Be Empty"
        }else
        {
            startActivity(Intent(this,SellActivity3::class.java))
        }

    }
}