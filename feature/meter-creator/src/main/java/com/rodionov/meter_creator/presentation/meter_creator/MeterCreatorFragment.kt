package com.rodionov.meter_creator.presentation.meter_creator

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentMeterCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorFragment.Companion.FLAT_ID
import dagger.Lazy
import javax.inject.Inject

class MeterCreatorFragment: BaseFragment(R.layout.fragment_meter_creator) {

    private val binding: FragmentMeterCreatorBinding by viewBinding(FragmentMeterCreatorBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: MeterCreatorViewModel by viewModels { viewModelFactory.get() }

    override val screenViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_meter_creator

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LOG_TAG", "onViewCreated: id = ${arguments?.getString(FLAT_ID)}")
        binding.btnSaveMeter.setOnClickListener {
            viewModel.navigate(R.id.action_meterCreatorFragment_to_meterInfoFragment)
        }
        binding.tvDayOfDataSend.text = binding.sbDay.progress.toString()
        binding.sbDay.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    Log.d("LOG_TAG", "onProgressChanged: progress = ${p0?.progress}, p1 = $p1, p2 = $p2")
                    binding.tvDayOfDataSend.text = p1.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    Log.d("LOG_TAG", "onStartTrackingTouch: progress  = ${p0?.progress}")

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    Log.d("LOG_TAG", "onStopTrackingTouch: progress = ${p0?.progress}")
                    binding.tvDayOfDataSend.text = p0?.progress.toString()
                }

            }
        )
    }

    private fun validate() = validateField(binding.tilMeterType) and validateField(binding.tilMeterName)

}