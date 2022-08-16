package com.rodionov.meter_data_input.presentation.start_input

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.databinding.FragmentStartInputBinding
import com.rodionov.meter_data_input.presentation.start_input.adapters.StartInputViewPagerAdapter

class StartInputFragment: Fragment(R.layout.fragment_start_input) { //TODO change to base fragment

    private val tabsName = listOf(
        com.rodionov.ui.R.string.tab_apartments,
        com.rodionov.ui.R.string.tab_meters,
    )

    private val binding: FragmentStartInputBinding by viewBinding(FragmentStartInputBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpMeters.adapter = StartInputViewPagerAdapter(this).apply {
            items = listOf()
        }
        TabLayoutMediator(binding.tabsGroupingType, binding.vpMeters) { tab, position ->
            tab.text = getString(tabsName[position])
        }.attach()
    }

//vpDriverFlights.adapter = ViewPagerAdapter(this).apply {
//            items = listOf(
//                DriverCurrentFlightFragment(),
//                DriverNextFlightFragment()
//            )
//        }
//        TabLayoutMediator(tabsDriverFlights, vpDriverFlights) { tab, position ->
//            tab.text = getString(tabsName[position])
//            tab.view.background
//            tabsDriverFlights
//
//        }.attach()
}