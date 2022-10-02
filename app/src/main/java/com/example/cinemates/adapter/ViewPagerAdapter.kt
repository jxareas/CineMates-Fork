package com.example.cinemates.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragments: MutableList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun createFragment(position: Int): Fragment = fragments.elementAt(position)


    override fun getItemCount(): Int = fragments.size

}