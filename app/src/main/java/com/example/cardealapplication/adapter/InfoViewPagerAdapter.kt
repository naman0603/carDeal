package com.example.cardealapplication.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cardealapplication.fragments.InfoDetailsFragment
import com.example.cardealapplication.fragments.InfoSpecificationFragment

class InfoViewPagerAdapter( fm:FragmentManager):FragmentStatePagerAdapter(fm){

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitle = ArrayList<String>()

    override fun getCount() = fragmentList.size

    override fun getItem(position: Int) = fragmentList[position]

    override fun getPageTitle(position: Int) = fragmentTitle[position]

    fun addFragment(fragment: Fragment,title : String){
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }
}