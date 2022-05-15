package com.rodionov.meter_creator.presentation.meter_info

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.FragmentMeterInfoBinding

class MeterInfoFragment: BaseFragment(R.layout.fragment_meter_info) {

    private val binding: FragmentMeterInfoBinding by viewBinding(FragmentMeterInfoBinding::bind)

    private val viewModel: MeterInfoViewModel by viewModels()

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
