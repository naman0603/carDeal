package com.example.cardealapplication.car_info

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.dataModel.InfoBrandDataModel
import com.example.cardealapplication.dataModel.InfoHyundaiModelData
import com.example.cardealapplication.dataModel.InfoMarutiModelData
import com.example.cardealapplication.dataModel.InfoTataModelData
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivityInfoBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityInfoBinding
    private val db = Firebase.firestore

    private var brandModelList:ArrayList<InfoBrandDataModel> = ArrayList()
    private var marutiModelList:ArrayList<InfoMarutiModelData> = ArrayList()
    private var hyundaiModelList:ArrayList<InfoHyundaiModelData> = ArrayList()
    private var tataModelList:ArrayList<InfoTataModelData> = ArrayList()
    private lateinit var carBrandList : List<String>
    private lateinit var marutiCarList : List<String>
    private lateinit var tataCarList : List<String>
    private lateinit var hyundaiCarList : List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()
    }
    private fun initView() {
        brandItemView()

        binding.btnSubmit.setOnClickListener {
            performValidation()
        }
    }
    private fun performValidation() {
        val brand=binding.txtBrand.text.toString()
        val model=binding.txtModel.text.toString()

        if(brand.isEmpty())
        {
            binding.txtBrand.error="Cannot Be Empty"
        }else if(model.isEmpty())
        {
            binding.txtModel.error="Cannot Be Empty"
        }else
        {
            val intent=Intent(this, InfoActivity2::class.java)
            intent.putExtra("CarName",model)
            startActivity(intent)
        }
    }
    private fun brandItemView() {

        db.collection("Cars Model")
            .get()
            .addOnCompleteListener { it ->
                if(it.isSuccessful){
                    for(document in it.result!!){
                        brandModelList.add(InfoBrandDataModel(document.data["Company"].toString(),
                            document.id))
                    }
                }
                carBrandList = brandModelList.map { it.brand }
                val adapter = ArrayAdapter(this,R.layout.list_item,carBrandList)
                binding.txtBrand.setAdapter(adapter)
            }
        binding.txtBrand.onItemClickListener=
            AdapterView.OnItemClickListener { _, _, position, _ ->
                when (position) {
                    0 -> {
                        marutiModelView()
                    }
                    1 -> {
                        tataModelView()
                    }
                    2 -> {
                        hyundaiModelView()
                    }
                }
            }
    }
    private fun hyundaiModelView() {
        tataModelList.clear()
        db.collection("Cars").whereEqualTo("model_id",brandModelList[2].model_id).
        get().addOnCompleteListener { it ->
            if(it.isSuccessful){
                for(document in it.result!!){
                    hyundaiModelList.add(InfoHyundaiModelData(document.data["car_name"].toString()))
                }
            }
            hyundaiCarList = hyundaiModelList.map { it.car_name }
            val adapter = ArrayAdapter(this,R.layout.list_item,hyundaiCarList)
            binding.txtModel.setAdapter(adapter)
        }
    }
    private fun tataModelView() {
        tataModelList.clear()
        db.collection("Cars").whereEqualTo("model_id",brandModelList[1].model_id).
        get().addOnCompleteListener { it ->
            if(it.isSuccessful){
                        for (document in it.result!!){
                            tataModelList.add(InfoTataModelData(document.data["car_name"].toString()))
                        }
                    }
                    tataCarList=tataModelList.map { it.car_name }
                    val adapter=ArrayAdapter(this,R.layout.list_item,tataCarList)
                    binding.txtModel.setAdapter(adapter)
                }
    }
    private fun marutiModelView() {
        marutiModelList.clear()
        db.collection("Cars").whereEqualTo("model_id",brandModelList[0].model_id).
        get().addOnCompleteListener { it ->
            if(it.isSuccessful){
                for(document in it.result!!){
                    marutiModelList.add(InfoMarutiModelData(document.data["car_name"].toString()))
                }
            }
            marutiCarList = marutiModelList.map { it.car_name }
            val adapter = ArrayAdapter(this,R.layout.list_item,marutiCarList)
            binding.txtModel.setAdapter(adapter)
        }
    }
}