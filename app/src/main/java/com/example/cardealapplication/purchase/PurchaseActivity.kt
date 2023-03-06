package com.example.cardealapplication.purchase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardealapplication.dataAdapter.PurchaseDataAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.databinding.ActivityPurchaseBinding

class PurchaseActivity : AppCompatActivity() {
    private lateinit var dataAdapter: PurchaseDataAdapter
    lateinit var binding: ActivityPurchaseBinding
    private var model=java.util.ArrayList<PurchaseDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        dataAdapter= PurchaseDataAdapter(this,model)
        binding.recyclerView.adapter=dataAdapter
    }


}