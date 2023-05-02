package com.example.cardealapplication.car_info

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardealapplication.dataAdapter.InfoCarDetailsDataAdapter
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.databinding.ActivityInfo2Binding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityInfo2Binding
    private val db = Firebase.firestore

    private var model=java.util.ArrayList<InfoCarDetailsDataModel>()
    private lateinit var dataAdapter: InfoCarDetailsDataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val carCompany: String = intent.getStringExtra("Company Name").toString()
        supportActionBar!!.title = ""+carCompany
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()
    }

    private fun initView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        dataAdapter= InfoCarDetailsDataAdapter(this,model)
        binding.recyclerView.adapter=dataAdapter
        addData()
        dataAdapter.onItemClick = {
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("Data",it)
            startActivity(intent)
        }

    }

    private fun addData() {
        val intent = intent
        val carCompany: String = intent.getStringExtra("Company Name").toString()
        db.collection("Display Info Cars").whereEqualTo("txtCompanyName",carCompany).
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        model.add(
                            InfoCarDetailsDataModel(
                                dc.document.data["txtCompanyName"].toString(),
                                dc.document.data["txtCarName"].toString(),
                                dc.document.data["txtPriceRange"].toString(),
                                dc.document.data["txtMileage"].toString(),
                                dc.document.data["txtEngine"].toString(),
                                dc.document.data["txtSeat"].toString(),
                                dc.document.data["txtFuelCapacity"].toString(),
                                dc.document.data["txtFuelType"].toString(),
                                dc.document.data["txtTransmission"].toString(),
                                dc.document.data["txtType"].toString(),
                                dc.document.data["txtLength"].toString(),
                                dc.document.data["txtWidth"].toString(),
                                dc.document.data["txtHeight"].toString(),
                                dc.document.data["txtDetails"].toString(),
                                dc.document.data["imgCarView"].toString()
                            )
                        )
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }
        })

    }
}