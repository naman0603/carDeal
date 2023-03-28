package com.example.cardealapplication.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cardealapplication.dataModel.InfoCarDetailsDataModel
import com.example.cardealapplication.fragments.InfoDetailsFragment
import com.example.cardealapplication.fragments.InfoSpecificationFragment

class InfoViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    data: InfoCarDetailsDataModel?
):
    FragmentStateAdapter(fm,lifecycle){
    private val txtCarName = data?.txtCarName
    private val txtPriceRange = data?.txtPriceRange
    private val txtMileage = data?.txtMileage
    private val txtEngine = data?.txtEngine
    private val txtSeat = data?.txtSeat
    private val txtFuelCapacity = data?.txtFuelCapacity
    private val txtFuelType = data?.txtFuelType
    private val txtTransmission = data?.txtTransmission
    private val txtType = data?.txtType
    private val txtLength = data?.txtLength
    private val txtWidth = data?.txtWidth
    private val txtHeight = data?.txtHeight
    private val txtDetails = data?.txtDetails
    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> {
               val bundle = Bundle()
               bundle.putString("txtDetails",txtDetails)
               val infoDetailsFragment = InfoDetailsFragment()
               infoDetailsFragment.arguments=bundle
               return infoDetailsFragment
           }
           1 -> {
               val bun = Bundle()

               bun.putString("txtCarName",txtCarName)
               bun.putString("txtPriceRange",txtPriceRange)
               bun.putString("txtMileage",txtMileage)
               bun.putString("txtEngine",txtEngine)
               bun.putString("txtSeat",txtSeat)
               bun.putString("txtFuelCapacity",txtFuelCapacity)
               bun.putString("txtTransmission",txtTransmission)
               bun.putString("txtType",txtType)
               bun.putString("txtFuelType",txtFuelType)
               bun.putString("txtLength",txtLength)
               bun.putString("txtWidth",txtWidth)
               bun.putString("txtHeight",txtHeight)

               val infoSpecificationFragment = InfoSpecificationFragment()
               infoSpecificationFragment.arguments = bun
               return infoSpecificationFragment
           }
           else -> {
               InfoDetailsFragment()
           }
       }
    }


}