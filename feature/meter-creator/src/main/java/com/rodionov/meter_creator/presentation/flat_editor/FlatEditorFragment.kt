package com.rodionov.meter_creator.presentation.flat_editor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentFlatEditorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.flat_editor.adapters.FlatEditorAdapter
import com.rodionov.meter_creator.presentation.start_creator.StartCreatorFragment.Companion.FLAT_ID
import com.rodionov.meter_creator.presentation.start_creator.StartCreatorViewModel
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FlatEditorFragment : BaseFragment(R.layout.fragment_flat_editor) {

    private val binding: FragmentFlatEditorBinding by viewBinding(FragmentFlatEditorBinding::bind)

    private val parentViewModel: CreatorViewModel by viewModels()

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: FlatEditorViewModel by viewModels{
        viewModelFactory.get()
    }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_flat_editor

    private val adapter: FlatEditorAdapter by lazy { FlatEditorAdapter(::saveMeter) }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.meters.onEach(::handleMeters).launchWithLifecycleStarted(lifecycleScope, lifecycle)
        val ids = requireArguments().getStringArrayList(FLAT_ID)?.toList()
        Log.d("LOG_TAG", "onCreate: ids size = ${ids?.size}")
        viewModel.getMeters(ids ?: arrayListOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMetersFlatEditor.adapter = adapter
    }

    private fun handleMeters(meters: List<Meter>) {
        Log.d("LOG_TAG", "handleMeters: size = ${meters.size}")
        adapter.submitList(meters)
    }

    private fun saveMeter(meter: Meter) {
        parentViewModel.meter = meter
        viewModel.navigate(R.id.action_flatEditorFragment_to_meterEditorFragment)
    }


}