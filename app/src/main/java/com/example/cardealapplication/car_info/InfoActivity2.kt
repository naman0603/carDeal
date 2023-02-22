package com.example.cardealapplication.car_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivityInfo2Binding

class InfoActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityInfo2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}