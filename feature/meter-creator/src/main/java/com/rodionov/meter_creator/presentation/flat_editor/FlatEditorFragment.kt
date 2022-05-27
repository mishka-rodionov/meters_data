package com.rodionov.meter_creator.presentation.flat_editor

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentFlatEditorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.start_creator.StartCreatorViewModel
import dagger.Lazy
import javax.inject.Inject

class FlatEditorFragment : BaseFragment(R.layout.fragment_flat_editor) {

    private val binding: FragmentFlatEditorBinding by viewBinding(FragmentFlatEditorBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: FlatEditorViewModel by viewModels{
        viewModelFactory.get()
    }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_flat_editor

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMeters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}