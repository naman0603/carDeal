package com.example.cardealapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cardealapplication.R

class InfoDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info_details, container, false)
        setData(view)
        return view
    }

    private fun setData(view: View) {
        val txtDetails = view.findViewById<TextView>(R.id.txtDetails)
       val details = arguments?.getString("txtDetails")

        txtDetails.text =details.toString()

    }
}