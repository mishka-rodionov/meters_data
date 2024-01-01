package com.rodionov.meter_data_input.presentation.start_input.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class StartInputViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var items: List<Fragment> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]
}