package com.example.cardealapplication.car_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.dataModel.InfoImagesModel
import com.example.cardealapplication.databinding.ActivityInfo2Binding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class InfoActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityInfo2Binding
    private val db = Firebase.firestore

    private var imgList= mutableListOf<CarouselItem>()
    private var imageModelList:List<InfoImagesModel.Images> = ArrayList()
    private lateinit var imageList : List<String>
    var measurement: List<InfoImagesModel.Images>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        setImg()
    }

    private fun setImg() {
        imgList.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080"
            )
        )
        imgList.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            )
        )
        binding.img.setData(imgList)
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val carName=intent.getStringExtra("CarName")

        db.collection("Cars").whereEqualTo("car_name",carName).get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    for (document in it.result!!){
                        binding.txtCarName.text = "Name : ${document.data["car_name"].toString()}"
                        binding.txtEngine.text = "Engine : ${document.data["engine"].toString()}"
                        binding.txtMileage.text = "Mileage : ${document.data["mileage"].toString()}"
                        binding.txtPrice.text = "Price : ${document.data["price"].toString()}"
                        binding.txtSeatingCapacity.text = "Seating Capacity : ${document.data["seating_capacity"].toString()}"
                        binding.txtTransmission.text = "Transmission : ${document.data["transmission"].toString()}"

/*
                        measurement = document.data["Image"] as List<InfoImagesModel.Images>?
*/
                    }
                }

            /*    for (i in 0 until measurement!!.size){


                    Log.v("imageModelList",""+ measurement!!.get(i).image_url)
                }*/

            }
    }
}