package com.rodionov.meters_data.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseActivity
import com.rodionov.meters_data.R
import com.rodionov.meters_data.data.di.MainViewModelFactory
import com.rodionov.meters_data.databinding.ActivityMainBinding
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var mainViewModelFactory: Lazy<MainViewModelFactory>

    private val viewModel: MainViewModel by viewModels {
        mainViewModelFactory.get()
    }

//    override val screenViewModel: BaseViewModel by lazy { viewModel }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        val navController = navHostFragment.navController
        viewModel.toLogin.onEach(::handleToLogin).launchWithLifecycleStarted(lifecycleScope, lifecycle)
        viewModel.checkFirstStart()
        initToolbar(navController)
        binding.mainBottomNavigation
            .setupWithNavController(navController)
    }

    private fun handleToLogin(toLogin: Boolean) {
        if (toLogin) {
            viewModel.navigate(R.id.loginFragment)
        }
    }

    private fun initToolbar(navController: NavController) {
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = ""
        setupActionBarWithNavController(navController = navController, AppBarConfiguration(navGraph = navController.graph, drawerLayout = null))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.mainContainer).navigateUp()
    }

}