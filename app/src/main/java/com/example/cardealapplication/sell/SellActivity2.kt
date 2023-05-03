package com.example.cardealapplication.sell

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySell2Binding

class SellActivity2 : AppCompatActivity() {

    lateinit var binding: ActivitySell2Binding
    private  var imageUri: Uri? = null
    private  var imageUploadUri: Uri? = null
    private val galleryRequestCode : Int = 101
    private val cameraCaptureCode : Int = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySell2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()
    }
    private fun initView() {

        binding.btnCaptureImg.setOnClickListener {
            popUp()
        }

        binding.btnContinue.setOnClickListener {
            performValidation()
        }
    }
    @SuppressLint("InflateParams", "UseCompatLoadingForDrawables")
    private fun popUp() {
        val dialogBinding = layoutInflater.inflate(R.layout.popup_photo_sell,null)
        val builder = Dialog(this)

        builder.setContentView(dialogBinding)

        val camera : ImageView = builder.findViewById(R.id.camera)
        val gallery : ImageView = builder.findViewById(R.id.gallery)

        camera.setOnClickListener {
            checkCameraPermission()
            builder.dismiss()
        }
        gallery.setOnClickListener {
            uploadImage()
            builder.dismiss()
        }
        builder.setCancelable(true)
        builder.window?.setBackgroundDrawable(getDrawable(R.drawable.popup_sell_bg))
        builder.show()

    }

    private fun checkCameraPermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED )
            {
                val permission = arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission,galleryRequestCode)
            }else{
                openCamera()
            }
        }
        else{
            openCamera()
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(cameraIntent,cameraCaptureCode)

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            galleryRequestCode->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera()
                }else{
                    Toast.makeText(this, "Please Give Required Permissions", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun uploadImage() {
        val intent =  Intent()

        intent.action= Intent.ACTION_GET_CONTENT
        intent.type="image/*"
        startActivityForResult(intent,galleryRequestCode)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == galleryRequestCode){
            binding.imageView.setImageURI(data?.data)
            imageUploadUri = data?.data

        }else if (requestCode == cameraCaptureCode){
            binding.imageView.setImageURI(imageUri)
           imageUploadUri = imageUri
        }
    }


    private fun performValidation() {

        val color = binding.txtColor.text.toString()
        val kms = binding.txtKms.text.toString()

       if(color.isEmpty())
        {
            binding.txtColor.error="Cannot Be Empty"
        }else if(kms.isEmpty())
        {
            binding.txtKms.error="Cannot Be Empty"
        }else if(binding.imageView.drawable == null)
       {
           Toast.makeText(this, "Please Upload an Image Of a Car", Toast.LENGTH_SHORT).show()
       }else
        {
            addData(color,kms)
        }
    }
    private fun addData( color: String, kms: String) {

        val txtCarName = intent.extras?.getString("txtCarName")
        val txtManufactureYear = intent.extras?.getString("txtManufactureYear")
        val txtFuelType = intent.extras?.getString("txtFuelType")
        val txtRegisteredState = intent.extras?.getString("txtRegisteredState")
        val txtCity = intent.extras?.getString("txtCity")
        val txtTransmission = intent.extras?.getString("txtTransmission")
        val txtOwners = intent.extras?.getString("txtOwners")
        val txtInsurance = intent.extras?.getString("txtInsurance")
        Toast.makeText(this, ""+txtCarName, Toast.LENGTH_SHORT).show()
        val intent = Intent(this,SellActivity3::class.java)

        intent.putExtra("txtCarName",txtCarName)
        intent.putExtra("txtManufactureYear",txtManufactureYear)
        intent.putExtra("txtFuelType",txtFuelType)
        intent.putExtra("txtRegisteredState",txtRegisteredState)
        intent.putExtra("txtCity",txtCity)
        intent.putExtra("txtTransmission",txtTransmission)
        intent.putExtra("txtOwners",txtOwners)
        intent.putExtra("txtInsurance",txtInsurance)
        intent.putExtra("txtColor",color)
        intent.putExtra("txtKms",kms)
        intent.putExtra("ImageUri",imageUploadUri.toString())


        startActivity(intent)
    }
}