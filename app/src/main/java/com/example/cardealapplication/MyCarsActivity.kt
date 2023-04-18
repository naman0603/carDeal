package com.example.cardealapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardealapplication.dataAdapter.MyCarsDataAdapter
import com.example.cardealapplication.dataModel.MyCarsDataModel
import com.example.cardealapplication.databinding.ActivityMyCarsBinding
import com.example.cardealapplication.purchase.PurchaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore.getInstance
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyCarsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyCarsBinding
    private var db = Firebase.firestore

    private var model=java.util.ArrayList<MyCarsDataModel>()
    private lateinit var dataAdapter: MyCarsDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        dataAdapter= MyCarsDataAdapter(this,model)
        binding.recyclerView.adapter=dataAdapter
        addData()
    }
    private fun addData() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        db= getInstance()

        db.collection("Sell Car").whereEqualTo("User Id",uid).
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        model.add(
                            MyCarsDataModel(
                                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5kEhz8kWPfT53ac6oiHZYs4je6WWxillLmQ&usqp=CAU",
                                dc.document.data["Expected Price"].toString(),
                                dc.document.data["Model"].toString(),
                                dc.document.data["Name"].toString(),
                                dc.document.data["Phone"].toString(),
                                dc.document.data["txtCarName"].toString(),
                                dc.document.data["txtCity"].toString(),
                                dc.document.data["txtColor"].toString(),
                                dc.document.data["txtFuelType"].toString(),
                                dc.document.data["txtInsurance"].toString(),
                                dc.document.data["txtKms"].toString(),
                                dc.document.data["txtOwners"].toString(),
                                dc.document.data["txtRegisteredState"].toString(),
                                dc.document.data["txtTransmission"].toString())
                        )
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }
        })
    }
}