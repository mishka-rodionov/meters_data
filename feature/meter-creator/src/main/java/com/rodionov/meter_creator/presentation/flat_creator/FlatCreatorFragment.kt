package com.rodionov.meter_creator.presentation.flat_creator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.FragmentFlatCreatorBinding

class FlatCreatorFragment : BaseFragment(R.layout.fragment_flat_creator) {

    private val binding: FragmentFlatCreatorBinding by viewBinding(FragmentFlatCreatorBinding::bind)

    override val screenViewModel: FlatCreatorViewModel by viewModels()

    override val toolbarTitle = R.string.toolbar_title_flat_creator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveFlat.setOnClickListener {
            screenViewModel.navigate(R.id.action_flatCreatorFragment_to_meterCreatorFragment)
        }
    }

}