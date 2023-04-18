package com.example.cardealapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutUsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.txtAboutUs.text = "• This unique app is designed in such a way that a user can fulfill its requirement of selling and purchasing car " +
                "in a single application without foundering into different applications.\n\n"+"• One of the most unique thing of this application is that not only user can " +
                "sell or purchase car but also get information of basics of cars which are required when purchasing new car. "
        val version = "<b>Version</b> :- "+ BuildConfig.VERSION_NAME
        val creator = "<b>Creator</b> :- 200OK Solutions"
        binding.txtVersion.text =Html.fromHtml(version)
        binding.txtCreator.text =Html.fromHtml(creator)
    }
}