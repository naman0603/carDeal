package com.example.cardealapplication.car_info

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cardealapplication.R
import com.example.cardealapplication.dataAdapter.InfoCarDetailsDataAdapter
import com.example.cardealapplication.dataAdapter.InfoCarModelDataAdapter
import com.example.cardealapplication.dataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.dataModel.InfoCarModelDataModel
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityInfo3Binding
import com.example.cardealapplication.databinding.ActivityInfoBinding
import com.example.cardealapplication.purchase.PurchaseActivity2
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoActivity3 : AppCompatActivity() {
    lateinit var binding : ActivityInfo3Binding
    private var db = Firebase.firestore

    private lateinit var dataAdapter: InfoCarModelDataAdapter
    private var model = ArrayList<InfoCarModelDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager= GridLayoutManager(this,3)
        dataAdapter = InfoCarModelDataAdapter(this,model)
        binding.recyclerView.adapter=dataAdapter

        addData()

        dataAdapter.onItemClick = {
            val intent = Intent(this, InfoActivity2::class.java)
            intent.putExtra("Car Name",it.txtCompanyName)
            startActivity(intent)
        }
    }

    private fun addData() {
        db.collection("Company Model").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        model.add(
                            InfoCarModelDataModel(
                                dc.document.data["logo"].toString(),
                                dc.document.data["name"].toString()
                        )
                        )
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }
        })
    }
}