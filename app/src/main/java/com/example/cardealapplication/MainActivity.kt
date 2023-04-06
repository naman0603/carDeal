package com.example.cardealapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.cardealapplication.databinding.ActivityMainBinding
import com.example.cardealapplication.fragments.HomeFragment
import com.example.cardealapplication.fragments.SellFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_home)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {

        binding.btmNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_Home->{replaceFragment(HomeFragment())
                    supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                    supportActionBar!!.setCustomView(R.layout.custom_action_bar_home)
                }
                R.id.nav_Buy->{
                    supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                    supportActionBar!!.setCustomView(R.layout.custom_action_bar_buy)
                }
                R.id.nav_Sell->{replaceFragment(SellFragment())
                    supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                    supportActionBar!!.setCustomView(R.layout.custom_action_bar_sell)
                }
                R.id.nav_Account->{
                    supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
                    supportActionBar!!.setCustomView(R.layout.custom_action_bar_account)
                }

            }
            true
        }

        replaceFragment(HomeFragment())
    }
    private fun replaceFragment(frag : Fragment){
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.frameLayout,frag)
        fm.commit()
    }
}