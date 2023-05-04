package com.example.cardealapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardealapplication.R
import com.example.cardealapplication.dataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.purchase.PurchaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BuyFragment : Fragment() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var dataAdapter: PurchaseDataAdapter
    private var model=java.util.ArrayList<PurchaseDataModel>()
    private var db = Firebase.firestore

    private lateinit var city : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
         recyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        dataAdapter= PurchaseDataAdapter(requireContext(),model)
        recyclerView.adapter=dataAdapter

        dataAdapter.onItemClick = {
            val intent = Intent(requireContext(), PurchaseActivity::class.java)
            intent.putExtra("Data",it)
            startActivity(intent)
        }

        addData()
    }

    private fun addData() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseFirestore.getInstance()
        val sp: SharedPreferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)
         city = sp.getString("txtCity", "").toString()

        db.collection("Sell Car")
            .whereNotEqualTo("User Id",uid)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.v("error", "" + error.message.toString())
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            model.add(
                                PurchaseDataModel(
                                    dc.document.data["Image Url"].toString(),
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
                                    dc.document.data["txtTransmission"].toString(),
                                    dc.document.data["txtCarNumber"].toString()
                                )
                            )
                        }
                    }
                    dataAdapter.notifyDataSetChanged()
                }
            })
    }
}