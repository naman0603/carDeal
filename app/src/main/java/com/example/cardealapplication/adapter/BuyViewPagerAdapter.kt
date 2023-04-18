package com.example.cardealapplication.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cardealapplication.dataModel.PurchaseDataModel
import com.example.cardealapplication.fragments.BuyOwnerDetailsFragment
import com.example.cardealapplication.fragments.BuySpecificationFragment
import com.example.cardealapplication.fragments.InfoDetailsFragment

class BuyViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle,data : PurchaseDataModel?): FragmentStateAdapter(fm,lifecycle) {
    private val Price = data?.Price
    private val Model = data?.Model
    private val Name = data?.Name
    private val txtCity = data?.txtCity
    private val txtColor = data?.txtColor
    private val txtFuelType = data?.txtFuelType
    private val txtInsurance = data?.txtInsurance
    private val txtKms = data?.txtKms
    private val txtOwners = data?.txtOwners
    private val txtRegisteredState = data?.txtRegisteredState
    private val txtTransmission = data?.txtTransmission

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                val bundle = Bundle()

                bundle.putString("Price",Price)
                bundle.putString("Model",Model)
                bundle.putString("txtColor",txtColor)
                bundle.putString("txtInsurance",txtInsurance)
                bundle.putString("txtKms",txtKms)
                bundle.putString("txtOwners",txtOwners)
                bundle.putString("txtTransmission",txtTransmission)
                bundle.putString("txtFuelType",txtFuelType)


                val buySpecificationFragment = BuySpecificationFragment()
                buySpecificationFragment.arguments = bundle
                return buySpecificationFragment

            }
            1 -> {
                val bun = Bundle()

                bun.putString("Name",Name)
                bun.putString("txtCity",txtCity)
                bun.putString("txtRegisteredState",txtRegisteredState)

                val buyOwnerDetailsFragment = BuyOwnerDetailsFragment()
                buyOwnerDetailsFragment.arguments = bun
                return buyOwnerDetailsFragment
            }
            else -> {
                InfoDetailsFragment()
            }
        }

    }
}