package com.example.nasa_app.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val Fragmentlistitles: MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return Fragmentlistitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Fragmentlistitles[position]
    }

    fun AddFragment(fragment: Fragment, Title: String) {
        fragmentList.add(fragment)
        Fragmentlistitles.add(Title)
    }
}