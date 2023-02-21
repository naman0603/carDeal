package com.example.cardealapplication.purchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardealapplication.DataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.DataModel.PurchaseDataModel
import com.example.cardealapplication.R

class PurchaseActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var dataAdapter: PurchaseDataAdapter
    var model=java.util.ArrayList<PurchaseDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()
        addData()

    }

    private fun addData() {
        model.add(PurchaseDataModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5kEhz8kWPfT53ac6oiHZYs4je6WWxillLmQ&usqp=CAU",
            "Nexon","2020","550000")
        )
        model.add(PurchaseDataModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5kEhz8kWPfT53ac6oiHZYs4je6WWxillLmQ&usqp=CAU",
            "Nexon","2022","700000")
        )

    }

    private fun initView() {
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        dataAdapter= PurchaseDataAdapter(this,model)
        recyclerView.adapter=dataAdapter
    }


}