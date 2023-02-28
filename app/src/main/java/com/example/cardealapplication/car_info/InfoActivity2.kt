package com.example.cardealapplication.car_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivityInfo2Binding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityInfo2Binding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()

    }

    private fun setData() {
        val carName=intent.getStringExtra("CarName")

        db.collection("Cars").whereEqualTo("car_name",carName).get()
            .addOnCompleteListener {it->
                if(it.isSuccessful){
                    for (document in it.result!!){
                        binding.txtCarName.text = "Name : ${document.data["car_name"].toString()}"
                        binding.txtEngine.text = "Engine : ${document.data["engine"].toString()}"
                        binding.txtMileage.text = "Mileage : ${document.data["mileage"].toString()}"
                        binding.txtPrice.text = "Price : ${document.data["price"].toString()}"
                        binding.txtSeatingCapacity.text = "Seating Capacity : ${document.data["seating_capacity"].toString()}"
                        binding.txtTransmission.text = "Transmission : ${document.data["transmission"].toString()}"

                    }
                }
            }

    }
}