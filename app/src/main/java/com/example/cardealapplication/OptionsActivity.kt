package com.example.cardealapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.example.cardealapplication.car_info.InfoActivity
import com.example.cardealapplication.purchase.PurchaseActivity
import com.example.cardealapplication.sell.SellActivity

class OptionsActivity : AppCompatActivity() {
    lateinit var btnBuy:Button
    lateinit var btnSell:Button
    lateinit var btnInfo:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        initView()
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)

    }

    private fun initView() {

        btnBuy=findViewById(R.id.btnBuy)
        btnSell=findViewById(R.id.btnSell)
        btnInfo=findViewById(R.id.btnInfo)

        btnBuy.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))

        }
        btnSell.setOnClickListener {
            startActivity(Intent(this,SellActivity::class.java))

        }

        btnInfo.setOnClickListener {
            startActivity(Intent(this,InfoActivity::class.java))

        }

    }
}