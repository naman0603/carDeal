package com.example.cardealapplication.fragments

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cardealapplication.R

class BuyOwnerDetailsFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buy_owner_details, container, false)
         setData(view)
         return view
    }

    private fun setData(view: View?) {
        val txtName = view?.findViewById<TextView>(R.id.Name)
        val txtState = view?.findViewById<TextView>(R.id.txtRegisteredState)
        val txtCity = view?.findViewById<TextView>(R.id.txtCity)

        view?.findViewById<TextView>(R.id.textViewName)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.textViewCity)?.typeface = Typeface.DEFAULT_BOLD
        view?.findViewById<TextView>(R.id.textViewState)?.typeface = Typeface.DEFAULT_BOLD
        val name = arguments?.getString("Name")
        val state = arguments?.getString("txtCity")
        val city = arguments?.getString("txtRegisteredState")

        txtName?.text = name.toString()
        txtState?.text = state.toString()
        txtCity?.text = city.toString()

    }
}