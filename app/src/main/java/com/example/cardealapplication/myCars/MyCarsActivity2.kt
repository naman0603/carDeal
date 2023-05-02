package com.example.cardealapplication.myCars

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cardealapplication.R
import com.example.cardealapplication.dataModel.MyCarsDataModel
import com.example.cardealapplication.databinding.ActivityMyCars2Binding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyCarsActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMyCars2Binding
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCars2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val data = intent.getParcelableExtra<MyCarsDataModel>("Data")
        supportActionBar!!.title = ""+data?.txtCarName
        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<MyCarsDataModel>("Data")

        binding.txtCarName.typeface = Typeface.DEFAULT_BOLD
        binding.txtName.typeface = Typeface.DEFAULT_BOLD
        binding.txtPhone.typeface = Typeface.DEFAULT_BOLD
        binding.txtPrice.typeface = Typeface.DEFAULT_BOLD

        Glide.with(this).load(data?.img).into(binding.imgCar)
        binding.Name.text = data?.Name.toString()
        binding.CarName.text = data?.txtCarName.toString()
        binding.Phone.text = data?.Phone.toString()
        binding.Price.text = data?.Price.toString()

        binding.btnDelete.setOnClickListener {
            deleteData(data)
        }

    }

    private fun deleteData(data: MyCarsDataModel?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Are You sure you do not want to sell your car ")
        builder.setIcon(R.drawable.ic_caution)

        builder.setPositiveButton("Yes"){ _, _ ->
            Log.v("DELETE_ID",""+data?.txtDocumentId.toString())
            db.collection("Sell Car").document(data?.txtDocumentId.toString()).delete().addOnSuccessListener {

                startActivity(Intent(this, MyCarsActivity::class.java))
                finish()
                Toast.makeText(applicationContext, " Successfully Removed ", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, " Error Occurred.\nPlease try again later ", Toast.LENGTH_SHORT).show()
                Log.e("ERROR_DELETE",""+it.message.toString())
            }

        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setCancelable(true)
        builder.show()

    }
}