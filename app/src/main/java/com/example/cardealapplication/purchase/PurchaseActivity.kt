package com.example.cardealapplication.purchase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardealapplication.dataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchaseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore.getInstance
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PurchaseActivity : AppCompatActivity() {
    private lateinit var dataAdapter: PurchaseDataAdapter
    lateinit var binding: ActivityPurchaseBinding
    private var model=java.util.ArrayList<PurchaseDataModel>()
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()
    }
    private fun addData() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        this.db = getInstance()
        db.collection("Sell Car").whereNotEqualTo("User Id",uid).
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                    for (dc:DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            model.add(PurchaseDataModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5kEhz8kWPfT53ac6oiHZYs4je6WWxillLmQ&usqp=CAU",
                                dc.document.data["Model"].toString(),
                                dc.document.data["Manufacture Year"].toString(),
                                dc.document.data["Expected Price"].toString()))
                        }
                    }
                dataAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        dataAdapter= PurchaseDataAdapter(this,model)
        binding.recyclerView.adapter=dataAdapter
        addData()
    }
}