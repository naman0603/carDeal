package com.example.cardealapplication.myTestDrive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardealapplication.dataAdapter.TestDriveDataAdapter
import com.example.cardealapplication.dataModel.TestDriveDataModel
import com.example.cardealapplication.databinding.ActivityMyTestDriveBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyTestDriveActivity : AppCompatActivity() {
    lateinit var binding : ActivityMyTestDriveBinding
    private var db = Firebase.firestore

    private var model=java.util.ArrayList<TestDriveDataModel>()
    private lateinit var dataAdapter: TestDriveDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTestDriveBinding.inflate(layoutInflater)
        supportActionBar!!.title = "My Test Drive "
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        dataAdapter= TestDriveDataAdapter(this,model)
        binding.recyclerView.adapter=dataAdapter

        dataAdapter.onItemClick = {
            val intent = Intent(this, MyTestDriveActivity2::class.java)
            intent.putExtra("Data",it)
            startActivity(intent)
        }


        addData()
    }

    private fun addData() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        db= FirebaseFirestore.getInstance()

        db.collection("Test Drive").whereEqualTo("uid",uid).
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        Log.v("DOCUMENT ID",""+dc.document.id)

                        val id = dc.document.id

                        model.add(
                            TestDriveDataModel(
                                dc.document.data["img"].toString(),
                                dc.document.data["txtCarName"].toString(),
                                dc.document.data["price"].toString(),
                                dc.document.data["address"].toString(),
                                dc.document.data["phone"].toString(),
                                dc.document.data["date"].toString(),
                                dc.document.data["time"].toString(),
                                dc.document.data["uid"].toString(),
                                dc.document.data["city"].toString(),
                                dc.document.data["state"].toString(),
                                dc.document.data["id"].toString(),
                                id
                                )
                        )
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }
        })

    }
}