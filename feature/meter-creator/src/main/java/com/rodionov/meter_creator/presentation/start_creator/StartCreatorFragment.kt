package com.rodionov.meter_creator.presentation.start_creator

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.dialog.MessageDialog
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentStartCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.start_creator.adapters.StartCreatorAdapter
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartCreatorFragment : BaseFragment(R.layout.fragment_start_creator) {

    private val binding: FragmentStartCreatorBinding by viewBinding(FragmentStartCreatorBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: StartCreatorViewModel by viewModels{
        viewModelFactory.get()
    }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_start_creator

    private val adapter: StartCreatorAdapter by lazy {
        StartCreatorAdapter(
            removeListener = viewModel::removeFlatListener,
            editListener = viewModel::editFlatListener
        )
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRealEstate.adapter = adapter
        binding.mcvFlatCreator.setOnClickListener {
            viewModel.navigate(R.id.action_startCreatorFragment_to_flatCreatorFragment)
        }
        viewModel.flats.onEach(::handleFlats).launchWithLifecycleStarted(lifecycleScope, lifecycle)
        viewModel.getFlats()
        MessageDialog.Builder().title("title").message("Message").buttonText("text").build().show(childFragmentManager)
    }

    private fun handleFlats(flats: List<Flat>?) {
        if (flats != null) {
            adapter.flats = flats
        }
    }

}