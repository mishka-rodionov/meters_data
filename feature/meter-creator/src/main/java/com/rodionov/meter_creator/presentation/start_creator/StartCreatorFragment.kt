package com.rodionov.meter_creator.presentation.start_creator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.FragmentStartCreatorBinding
import com.rodionov.meter_creator.presentation.start_creator.adapters.StartCreatorAdapter
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class StartCreatorFragment : BaseFragment(R.layout.fragment_start_creator) {

    private val binding: FragmentStartCreatorBinding by viewBinding(FragmentStartCreatorBinding::bind)

    private val viewModel: StartCreatorViewModel by viewModels()

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_start_creator

    private val adapter: StartCreatorAdapter by lazy {
        StartCreatorAdapter(
            removeListener = viewModel::removeFlatListener,
            editListener = viewModel::editFlatListener
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRealEstate.adapter = adapter
        binding.mcvFlatCreator.setOnClickListener {
            viewModel.navigate(R.id.action_startCreatorFragment_to_flatCreatorFragment)
        }
        viewModel.flats.onEach(::handleFlats).launchWithLifecycleStarted(lifecycleScope, lifecycle)
        viewModel.getFlats()
    }

    private fun handleFlats(flats: List<Flat>?) {
        if (flats != null) {
            adapter.flats = flats
        }
    }

}