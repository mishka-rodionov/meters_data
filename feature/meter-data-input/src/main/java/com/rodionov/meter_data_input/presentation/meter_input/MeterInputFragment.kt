package com.rodionov.meter_data_input.presentation.meter_input

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.databinding.FragmentMeterInputBinding
import com.rodionov.meter_data_input.di.DataInputViewModel
import com.rodionov.utils.extensions.nextFocusAfterFullFill
import com.rodionov.utils.extensions.showSoftInputKeyboard
import dagger.Lazy
import javax.inject.Inject

class MeterInputFragment: BaseFragment(R.layout.fragment_meter_input) {

    override val toolbarTitle = R.string.toolbar_title_meter_input

    private val binding: FragmentMeterInputBinding by viewBinding(FragmentMeterInputBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MeterInputViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etThousandsDigit.requestFocus()
        binding.etThousandsDigit.showSoftInputKeyboard(requireContext())
        nextFocusCorresponding()
    }

    private fun nextFocusCorresponding() {
        binding.etThousandsDigit.nextFocusAfterFullFill(binding.etHundredsDigit, 1)
        binding.etHundredsDigit.nextFocusAfterFullFill(binding.etTensDigit, 1)
        binding.etTensDigit.nextFocusAfterFullFill(binding.etUnitsDigit, 1)
        binding.etUnitsDigit.nextFocusAfterFullFill(binding.etTenthsDigit, 1)
        binding.etTenthsDigit.nextFocusAfterFullFill(binding.etHundredthsDigit, 1)
        binding.etHundredthsDigit.nextFocusAfterFullFill(binding.etThousandthsDigit, 1)
    }


}