package com.example.cardealapplication.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardealapplication.R
import com.example.cardealapplication.car_info.InfoActivity
import com.example.cardealapplication.dataAdapter.InfoRecommendedCarsDataAdapter
import com.example.cardealapplication.dataAdapter.MyCarsDataAdapter
import com.example.cardealapplication.dataModel.HomeRecommendedCarsModelData
import com.example.cardealapplication.dataModel.MyCarsDataModel
import com.example.cardealapplication.purchase.PurchaseActivity
import com.example.cardealapplication.viewHolder.InfoRecommendedCarsViewHolder
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    lateinit var recyclerView:RecyclerView
    private var model=java.util.ArrayList<HomeRecommendedCarsModelData>()
    private lateinit var dataAdapter: InfoRecommendedCarsDataAdapter

    var db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addData()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        dataAdapter = InfoRecommendedCarsDataAdapter(requireContext(),model)
        recyclerView.adapter=dataAdapter
        onClick()
    }

    private fun onClick() {
        view?.findViewById<CardView>(R.id.crdAudi)?.setOnClickListener {
            startActivity(Intent(requireContext(),InfoActivity::class.java))
        }

    }

    private fun addData() {
        db = FirebaseFirestore.getInstance()

        db.collection("Recommended Cars").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        model.add(HomeRecommendedCarsModelData(
                            dc.document.data["imgLogo"].toString(),
                            dc.document.data["imgCarImage"].toString(),
                            dc.document.data["txtName"].toString(),
                            dc.document.data["txtType"].toString(),
                            dc.document.data["txtPrice"].toString()
                        ))
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }
        })
    }
}