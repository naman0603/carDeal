package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
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

        typeItemView()
        transmissionItemView()
        ownersItemView()

        binding.btnContinue.setOnClickListener {
            performValidation()
        }

    }


    private fun ownersItemView() {

        val ownersItem = listOf("1","2","3","4")
        val adapter=ArrayAdapter(this,R.layout.list_item,ownersItem)
        binding.txtOwners.setAdapter(adapter)
    }

    private fun transmissionItemView() {

        val transmissionItems = listOf("1","2","3","4")
        val adapter=ArrayAdapter(this,R.layout.list_item,transmissionItems)
        binding.txtTransmission.setAdapter(adapter)

    }

    private fun typeItemView() {

        val typeItem = listOf("1","2","3","4")
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
            startActivity(Intent(this,SellActivity3::class.java))
        }

    }
}