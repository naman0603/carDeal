package com.example.cardealapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.authentication.LoginActivity
import com.example.cardealapplication.car_info.InfoActivity
import com.example.cardealapplication.databinding.ActivityOptionsBinding
import com.example.cardealapplication.purchase.PurchaseActivity
import com.example.cardealapplication.sell.SellActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OptionsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        initView()
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
    }

    private fun initView() {


        binding.btnBuy.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))

        }
        binding.btnSell.setOnClickListener {
            startActivity(Intent(this,SellActivity::class.java))

        }

        binding.btnInfo.setOnClickListener {
            startActivity(Intent(this,InfoActivity::class.java))

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnLogout->{
                auth.signOut()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            R.id.btnAccount->{
                startActivity(Intent(this,AccountActivity::class.java))

            }
        }
        return super.onOptionsItemSelected(item)
    }
}