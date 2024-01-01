package com.rodionov.meter_data_input.presentation.start_input

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewpager.widget.ViewPager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.databinding.FragmentStartInputBinding
import com.rodionov.meter_data_input.di.DataInputViewModel
import com.rodionov.meter_data_input.presentation.apartments.ApartmentsFragment
import com.rodionov.meter_data_input.presentation.meters.MetersFragment
import com.rodionov.meter_data_input.presentation.start_input.adapters.StartInputViewPagerAdapter
import dagger.Lazy
import javax.inject.Inject

class StartInputFragment: BaseFragment(R.layout.fragment_start_input) { //TODO change to base fragment

    private val tabsName = listOf(
        com.rodionov.ui.R.string.tab_apartments,
        com.rodionov.ui.R.string.tab_meters,
    )

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: StartInputViewModel by viewModels { viewModelFactory.get() }

    private val binding: FragmentStartInputBinding by viewBinding(FragmentStartInputBinding::bind)

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpMeters.adapter = StartInputViewPagerAdapter(this).apply {
            items = listOf(ApartmentsFragment(), MetersFragment())
        }
        TabLayoutMediator(binding.tabsGroupingType, binding.vpMeters) { tab, position ->
            tab.text = getString(tabsName[position])
        }.attach()
    }

}