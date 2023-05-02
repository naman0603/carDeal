package com.example.cardealapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cardealapplication.*
import com.example.cardealapplication.authentication.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.cardealapplication.myCars.MyCarsActivity


class AccountFragment : Fragment() {
    val  auth = Firebase.auth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_account, container, false)
        setData(view)

        return view
    }

    private fun setData(view: View?) {
        val text = view?.findViewById<TextView>(R.id.text)
        val version = "Version " + BuildConfig.VERSION_NAME
        text?.text = version
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logOut = view.findViewById<TextView>(R.id.txtLogOut)
        logOut.setOnClickListener {
            startActivity(Intent(requireContext(),LoginActivity::class.java))
            activity?.finish()
            auth.signOut()
        }

        val myProfile = view.findViewById<TextView>(R.id.txtProfile)
        myProfile.setOnClickListener {
            startActivity(Intent(requireContext(),AccountActivity::class.java))
        }

        val myCars = view.findViewById<TextView>(R.id.txtMyCars)
        myCars.setOnClickListener {
            startActivity(Intent(requireContext(), MyCarsActivity::class.java))
        }

        val aboutUs = view.findViewById<TextView>(R.id.txtAboutUs)
        aboutUs.setOnClickListener {
            startActivity(Intent(requireContext(),AboutUsActivity::class.java))
        }

        val changePass = view.findViewById<TextView>(R.id.txtChangePassword)
        changePass.setOnClickListener {
            startActivity(Intent(requireContext(),ChangePasswordActivity::class.java))
        }
    }
}