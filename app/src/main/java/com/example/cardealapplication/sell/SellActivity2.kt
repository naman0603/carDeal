package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
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


        binding.btnContinue.setOnClickListener {
            performValidation()
        }
    }

    private fun performValidation() {

        val color = binding.txtColor.text.toString()
        val kms = binding.txtKms.text.toString()

       if(color.isEmpty())
        {
            binding.txtColor.error="Cannot Be Empty"
        }else if(kms.isEmpty())
        {
            binding.txtKms.error="Cannot Be Empty"
        }else
        {
            addData(color,kms)
        }
    }
    private fun addData( color: String, kms: String) {

        val txtCarName = intent.extras?.getString("txtCarName")
        val txtManufactureYear = intent.extras?.getString("txtManufactureYear")
        val txtFuelType = intent.extras?.getString("txtFuelType")
        val txtRegisteredState = intent.extras?.getString("txtRegisteredState")
        val txtCity = intent.extras?.getString("txtCity")
        val txtTransmission = intent.extras?.getString("txtTransmission")
        val txtOwners = intent.extras?.getString("txtOwners")
        val txtInsurance = intent.extras?.getString("txtInsurance")
        Toast.makeText(this, ""+txtCarName, Toast.LENGTH_SHORT).show()
        val intent = Intent(this,SellActivity3::class.java)



        intent.putExtra("txtCarName",txtCarName)
        intent.putExtra("txtManufactureYear",txtManufactureYear)
        intent.putExtra("txtFuelType",txtFuelType)
        intent.putExtra("txtRegisteredState",txtRegisteredState)
        intent.putExtra("txtCity",txtCity)
        intent.putExtra("txtTransmission",txtTransmission)
        intent.putExtra("txtOwners",txtOwners)
        intent.putExtra("txtInsurance",txtInsurance)
        intent.putExtra("txtColor",color)
        intent.putExtra("txtKms",kms)

        startActivity(intent)
    }
}