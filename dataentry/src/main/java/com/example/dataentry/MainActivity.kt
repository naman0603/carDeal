package com.example.dataentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dataentry.dataModel.CarsDataModel
import com.example.dataentry.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        setCompanyName()

        binding.btnContinue.setOnClickListener {
            val txtCompanyName = binding.txtCompanyName.text.toString()
            val txtCarName = binding.txtCarName.text.toString()
            val txtPriceRange = binding.txtPriceRange.text.toString()
            val txtMileage = binding.txtMileage.text.toString()
            val txtEngine = binding.txtEngine.text.toString()
            val txtSeat = binding.txtSeat.text.toString()
            val txtFuelCapacity = binding.txtFuelCapacity.text.toString()
            val txtFuelType = binding.txtFuelType.text.toString()
            val txtTransmission = binding.txtTransmission.text.toString()
            val txtType = binding.txtType.text.toString()
            val txtLength = binding.txtLength.text.toString()
            val txtWidth = binding.txtWidth.text.toString()
            val txtHeight = binding.txtHeight.text.toString()
            val txtDetails = binding.txtDetails.text.toString()

            val carsDataModel = CarsDataModel(txtCompanyName,txtCarName,txtPriceRange,txtMileage,txtEngine,txtSeat,txtFuelCapacity,txtFuelType,
                txtTransmission,txtType,txtLength,txtWidth,txtHeight,txtDetails)
            addData(carsDataModel)
        }
    }

    private fun setCompanyName() {
        val brandItems =  listOf("Maruti","Hyundai","Honda","Tata","Mahindra","Renault","Toyota","MG","Kia","Audi","BMW","Mercedes-Benz","Skoda","Volkswagen","Jaguar","Volvo","Lexus","Land Rover","Porsche")
        val adapter = ArrayAdapter(this, R.layout.list_item,brandItems )
        binding.txtCompanyName.setAdapter(adapter)

    }

    private fun addData(model: CarsDataModel) {
        db.collection("Display Info Cars").document().set(model).
        addOnSuccessListener {
            startActivity(Intent(this,MainActivity::class.java))
            Toast.makeText(this, model.txtCompanyName+" "+model.txtCarName+"\nSuccessfully Added", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "UnSuccessful", Toast.LENGTH_SHORT).show()
        }
    }
}