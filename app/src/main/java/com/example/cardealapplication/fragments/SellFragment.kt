package com.example.cardealapplication.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardealapplication.R
import com.example.cardealapplication.R.*
import com.example.cardealapplication.car_info.InfoActivity2
import com.example.cardealapplication.dataAdapter.InfoCarModelDataAdapter
import com.example.cardealapplication.dataModel.InfoCarModelDataModel
import com.example.cardealapplication.databinding.ActivityInfo3Binding
import com.example.cardealapplication.sell.SellActivity
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SellFragment : Fragment() {
    private var db = Firebase.firestore

    private lateinit var dataAdapter: InfoCarModelDataAdapter
    private var model = ArrayList<InfoCarModelDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_sell, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)
        recyclerView!!.setHasFixedSize(true)
        recyclerView.layoutManager= GridLayoutManager(context,3)
        dataAdapter = InfoCarModelDataAdapter(requireContext(),model)
        recyclerView.adapter=dataAdapter

        addData()

        dataAdapter.onItemClick = {
            val intent = Intent(context, SellActivity::class.java)
            intent.putExtra("Company Name",it.txtCompanyName)
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