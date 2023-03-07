package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySell2Binding

class SellActivity2 : AppCompatActivity() {

    lateinit var binding: ActivitySell2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySell2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()

    }

    private fun initView() {

        insuranceItemView()
        transmissionItemView()
        ownersItemView()

        binding.btnContinue.setOnClickListener {
            performValidation()
        }

    }


    private fun ownersItemView() {

        val ownersItem = listOf("1","2","3","4","5")
        val adapter=ArrayAdapter(this,R.layout.list_item,ownersItem)
        binding.txtOwners.setAdapter(adapter)
    }

    private fun transmissionItemView() {

        val transmissionItems = listOf("Manual Transmission (MT)","Automatic Transmission (AMT)",
            "Semi-Automatic transmission (SAT)","Continuously Variable transmission (CVT)")
        val adapter=ArrayAdapter(this,R.layout.list_item,transmissionItems)
        binding.txtTransmission.setAdapter(adapter)

    }

    private fun insuranceItemView() {

        val typeItem = listOf("Yes","No")
        val adapter=ArrayAdapter(this,R.layout.list_item,typeItem)
        binding.txtInsurance.setAdapter(adapter)


    }


    private fun performValidation() {

        val insurance =binding.txtInsurance.text.toString()
        val transmission = binding.txtTransmission.text.toString()
        val owners = binding.txtOwners.text.toString()
        val color = binding.txtColor.text.toString()
        val kms = binding.txtKms.text.toString()

        if(insurance.isEmpty())
        {
            binding.txtInsurance.error="Cannot Be Empty"
        }else if(transmission.isEmpty())
        {
            binding.txtTransmission.error="Cannot Be Empty"
        }else if(owners.isEmpty())
        {
            binding.txtOwners.error="Cannot Be Empty"
        }else if(color.isEmpty())
        {
            binding.txtColor.error="Cannot Be Empty"
        }else if(kms.isEmpty())
        {
            binding.txtKms.error="Cannot Be Empty"
        }else
        {
            addData(insurance,transmission,owners,color,kms)

        }

    }

    private fun addData(insurance: String, transmission: String, owners: String, color: String, kms: String) {

        var brand = intent.extras?.getString("brand")
        var model = intent.extras?.getString("model")
        var variant = intent.extras?.getString("variant")
        var year = intent.extras?.getString("year")
        var state = intent.extras?.getString("state")
        Log.v("Data",""+brand+"\n"+model+"\n"+variant+"\n"+year+"\n"+state+"\n")
        var intent = Intent(this,SellActivity3::class.java)

        intent.putExtra("brand",brand)
        intent.putExtra("model",model)
        intent.putExtra("variant",variant)
        intent.putExtra("year",year)
        intent.putExtra("state",state)
        intent.putExtra("insurance",insurance)
        intent.putExtra("transmission",transmission)
        intent.putExtra("owners",owners)
        intent.putExtra("color",color)
        intent.putExtra("kms",kms)

        startActivity(intent)


    }

}