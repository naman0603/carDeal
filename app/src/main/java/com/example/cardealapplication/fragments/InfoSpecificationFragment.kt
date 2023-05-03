package com.example.cardealapplication.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cardealapplication.R


class InfoSpecificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info_specifications, container, false)
        setData(view)
        return view
    }

    private fun setData(view: View) {

        val txtPriceRange = view.findViewById<TextView>(R.id.txtPriceRange)
        val txtMileage = view.findViewById<TextView>(R.id.txtMileage)
        val txtEngine = view.findViewById<TextView>(R.id.txtEngine)
        val txtSeat = view.findViewById<TextView>(R.id.txtSeat)
        val txtFuelCapacity = view.findViewById<TextView>(R.id.txtFuelCapacity)
        val txtTransmission = view.findViewById<TextView>(R.id.txtTransmission)
        val txtType = view.findViewById<TextView>(R.id.txtType)
        val txtFuelType = view.findViewById<TextView>(R.id.txtFuelType)
        val txtLength = view.findViewById<TextView>(R.id.txtLength)
        val txtWidth = view.findViewById<TextView>(R.id.txtWidth)
        val txtHeight = view.findViewById<TextView>(R.id.txtHeight)

        view.findViewById<TextView>(R.id.txtView1)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView2)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView3)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView4)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView5)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView6)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView7)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView9)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView8)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView10)?.typeface = Typeface.DEFAULT_BOLD
        view.findViewById<TextView>(R.id.txtView11)?.typeface = Typeface.DEFAULT_BOLD




        val priceRange = arguments?.getString("txtPriceRange")
        val engine = arguments?.getString("txtEngine")
        val seat = arguments?.getString("txtSeat")
        val fuelCapacity = arguments?.getString("txtFuelCapacity")
        val transmission = arguments?.getString("txtTransmission")
        val type = arguments?.getString("txtType")
        val fuelType = arguments?.getString("txtFuelType")
        val length = arguments?.getString("txtLength")
        val width = arguments?.getString("txtWidth")
        val height = arguments?.getString("txtHeight")
        val mileage = arguments?.getString("txtMileage")

        txtPriceRange.text =priceRange.toString()
        txtEngine.text =engine.toString()
        txtSeat.text =seat.toString()
        txtFuelCapacity.text =fuelCapacity.toString()
        txtTransmission.text =transmission.toString()
        txtType.text =type.toString()
        txtFuelType.text =fuelType.toString()
        txtLength.text =length.toString()
        txtWidth.text =width.toString()
        txtHeight.text =height.toString()
        txtMileage.text= mileage.toString()

    }

}