package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.DataModel.SellBrandDataModel
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySellBinding

class SellActivity : AppCompatActivity() {

    lateinit var binding: ActivitySellBinding
   private var model: ArrayList<SellBrandDataModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()


    }

    private fun addBrandData() {
        model.add(SellBrandDataModel("1", "Maruti"))
        model.add(SellBrandDataModel("2","Hyundai"))
        model.add(SellBrandDataModel("3","Tata"))


        Log.d("listsize",""+model.size)
    }

    private fun initView() {

        brandItemView()
        modelItemView()
        variantItemView()
        yearItemView()
        stateItemView()

        binding.btnContinue.setOnClickListener {
            performValidation()
        }

    }

    private fun performValidation() {
        val Brand = binding.txtBrand.text.toString()
        val Model = binding.txtModel.text.toString()
        val Variant = binding.txtVariant.text.toString()
        val Year = binding.txtYear.text.toString()
        val State = binding.txtState.text.toString()

        if (Brand.isEmpty()) {
            binding.txtBrand.error = "Cannot Be Empty"
        } else if (Model.isEmpty()) {
            binding.txtModel.error = "Cannot Be Empty"
        } else if (Variant.isEmpty()) {
            binding.txtVariant.error = "Cannot Be Empty"
        } else if (Year.isEmpty()) {
            binding.txtYear.error = "Cannot Be Empty"
        } else if (State.isEmpty()) {
            binding.txtState.error = "Cannot Be Empty"
        } else {
            startActivity(Intent(this, SellActivity2::class.java))
        }

    }


    private fun yearItemView() {
        val yearItems = listOf("1", "2", "3", "4")
        val adapter = ArrayAdapter(this, R.layout.list_item, yearItems)
        binding.txtYear.setAdapter(adapter)
    }

    private fun stateItemView() {
        val stateItems = listOf("1", "2", "3", "4")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtState.setAdapter(adapter)
    }

    private fun variantItemView() {
        val variantItems = listOf("1", "2", "3", "4")
        val adapter = ArrayAdapter(this, R.layout.list_item, variantItems)
        binding.txtVariant.setAdapter(adapter)
    }

    private fun modelItemView() {
        val modelItems = listOf("1", "2", "3", "4")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }

    private fun brandItemView() {
        addBrandData()
        val brandItems =  listOf(
            model[0].brand.toString(),
            model[1].brand.toString(),
            model[2].brand.toString()
        )
        val adapter = ArrayAdapter(this, R.layout.list_item,brandItems )
        binding.txtBrand.setAdapter(adapter)

    }
}