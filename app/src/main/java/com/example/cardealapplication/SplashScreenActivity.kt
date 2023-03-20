package com.example.cardealapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.cardealapplication.authentication.LoginActivity
import com.example.cardealapplication.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        },2000)
    }
}