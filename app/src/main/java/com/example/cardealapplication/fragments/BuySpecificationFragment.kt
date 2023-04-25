package com.example.cardealapplication.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cardealapplication.R

class BuySpecificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view =  inflater.inflate(R.layout.fragment_buy_specification, container, false)
        setData(view)
        return view
    }

    private fun setData(view: View?) {
        val price = view?.findViewById<TextView>(R.id.Price)
        val model = view?.findViewById<TextView>(R.id.Model)
        val txtColor = view?.findViewById<TextView>(R.id.txtColor)
        val txtKms = view?.findViewById<TextView>(R.id.txtKms)
        val txtFuelType = view?.findViewById<TextView>(R.id.txtFuelType)
        val txtTransmission = view?.findViewById<TextView>(R.id.txtTransmission)
        val txtOwners = view?.findViewById<TextView>(R.id.txtOwners)
        val txtInsurance = view?.findViewById<TextView>(R.id.txtInsurance)

        view?.findViewById<TextView>(R.id.txtViewColor)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewFuel)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewInsurance)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewKms)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewOwners)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewPrice)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewYear)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.txtViewTransmission)?.typeface = Typeface.DEFAULT_BOLD


        val prices = "<b>₹ </b>"+arguments?.getString("Price").toString()
        val models = arguments?.getString("Model")
        val color = arguments?.getString("txtColor")
        val kms = arguments?.getString("txtKms").toString() + " kms"
        val fuelType = arguments?.getString("txtFuelType")
        val transmission = arguments?.getString("txtTransmission")
        val owners = arguments?.getString("txtOwners")
        val insurance = arguments?.getString("txtInsurance")



        price?.text = Html.fromHtml(prices)
        model?.text = models.toString()
        txtColor?.text = color.toString()
        txtKms?.text = kms
        txtFuelType?.text = fuelType.toString()
        txtTransmission?.text = transmission.toString()
        txtOwners?.text = owners.toString()
        txtInsurance?.text = insurance.toString()


    }
}