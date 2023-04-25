package com.example.cardealapplication.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.car_info.InfoActivity
import com.example.cardealapplication.car_info.InfoActivity2
import com.example.cardealapplication.car_info.InfoActivity3
import com.example.cardealapplication.dataAdapter.InfoRecommendedCarsDataAdapter
import com.example.cardealapplication.dataModel.HomeRecommendedCarsModelData
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.dataModel.InfoCarModelDataModel
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
   private lateinit var recyclerView:RecyclerView
    private lateinit var iv1:ImageView
    private  lateinit var iv2:ImageView
    private  lateinit var iv3:ImageView
    private  lateinit var iv4:ImageView
    private lateinit var iv5:ImageView
    private lateinit var iv6:ImageView

    private lateinit var tv1:TextView
    private  lateinit var tv2:TextView
    private  lateinit var tv3:TextView
    private  lateinit var tv4:TextView
    private lateinit var tv5:TextView
    private  lateinit var tv6:TextView

    private var model=java.util.ArrayList<InfoCarDetailsDataModel>()
    private lateinit var dataAdapter: InfoRecommendedCarsDataAdapter
    private var dataModel = ArrayList<InfoCarModelDataModel>()

    private var db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeList()

        recyclerView = view.findViewById(R.id.recyclerView)
        iv1 = view.findViewById(R.id.iv1)
        iv2 = view.findViewById(R.id.iv2)
        iv3 = view.findViewById(R.id.iv3)
        iv4 = view.findViewById(R.id.iv4)
        iv5 = view.findViewById(R.id.iv5)
        iv6 = view.findViewById(R.id.iv6)

        tv1 = view.findViewById(R.id.tv1)
        tv2 = view.findViewById(R.id.tv2)
        tv3 = view.findViewById(R.id.tv3)
        tv4 = view.findViewById(R.id.tv4)
        tv5 = view.findViewById(R.id.tv5)
        tv6 = view.findViewById(R.id.tv6)

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        dataAdapter = InfoRecommendedCarsDataAdapter(requireContext(),model)
        recyclerView.adapter=dataAdapter
        addData()
        dataAdapter.onItemClick = {

            val intent = Intent(requireContext(), InfoActivity::class.java)
            intent.putExtra("Data",it)
            startActivity(intent)
        }
        onClick()
    }

    private fun makeList() {

        db.collection("Company Model").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                      dataModel.add(
                          InfoCarModelDataModel(
                              dc.document.data["logo"].toString(),
                              dc.document.data["name"].toString()
                          )
                      )

                    }
                }
                setData()
            }
        })
    }

    private fun setData() {
        Glide.with(requireContext()).load(dataModel[0].imgLogo).into(iv1)
        Glide.with(requireContext()).load(dataModel[1].imgLogo).into(iv2)
        Glide.with(requireContext()).load(dataModel[2].imgLogo).into(iv3)
        Glide.with(requireContext()).load(dataModel[3].imgLogo).into(iv4)
        Glide.with(requireContext()).load(dataModel[4].imgLogo).into(iv5)
        Glide.with(requireContext()).load(dataModel[5].imgLogo).into(iv6)

        tv1.text = dataModel[0].txtCompanyName
        tv2.text = dataModel[1].txtCompanyName
        tv3.text = dataModel[2].txtCompanyName
        tv4.text = dataModel[3].txtCompanyName
        tv5.text = dataModel[4].txtCompanyName
        tv6.text = dataModel[5].txtCompanyName


    }

    private fun onClick() {
        view?.findViewById<CardView>(R.id.crd6)?.setOnClickListener {
            val intent = Intent(requireContext(), InfoActivity2::class.java)
            intent.putExtra("Company Name",dataModel[5].txtCompanyName)
            startActivity(intent)
        }
        view?.findViewById<CardView>(R.id.crd2)?.setOnClickListener {
            val intent = Intent(requireContext(), InfoActivity2::class.java)
            intent.putExtra("Company Name",dataModel[1].txtCompanyName)
            startActivity(intent)
        }
        view?.findViewById<CardView>(R.id.crd1)?.setOnClickListener {
            val intent = Intent(requireContext(), InfoActivity2::class.java)
            intent.putExtra("Company Name",dataModel[0].txtCompanyName)
            startActivity(intent)

        }
        view?.findViewById<CardView>(R.id.crd3)?.setOnClickListener {
            val intent = Intent(requireContext(), InfoActivity2::class.java)
            intent.putExtra("Company Name",dataModel[2].txtCompanyName)
            startActivity(intent)

        }
        view?.findViewById<CardView>(R.id.crd4)?.setOnClickListener {
            val intent = Intent(requireContext(), InfoActivity2::class.java)
            intent.putExtra("Company Name",dataModel[3].txtCompanyName)
            startActivity(intent)

        }
        view?.findViewById<CardView>(R.id.crd5)?.setOnClickListener {
            val intent = Intent(requireContext(), InfoActivity2::class.java)
            intent.putExtra("Company Name",dataModel[4].txtCompanyName)
            startActivity(intent)
        }

        view?.findViewById<TextView>(R.id.textViewClick)?.setOnClickListener {
            startActivity(Intent(requireContext(), InfoActivity3::class.java))
        }
    }

    private fun addData() {
        db = FirebaseFirestore.getInstance()

        db.collection("Display Info Cars").whereEqualTo("type","recommended").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        model.add(InfoCarDetailsDataModel(
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
                        ))
                    }
                }
                dataAdapter.notifyDataSetChanged()
            }
        })
    }
}