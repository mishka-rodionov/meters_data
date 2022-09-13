package com.rodionov.meters_data.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.interaction.NavigationExecutor
import com.rodionov.base.platform.BaseActivity
import com.rodionov.meters_data.R
import com.rodionov.meters_data.databinding.ActivityMainBinding
import com.rodionov.meters_data.presentation.login.LoginActivity

class MainActivity : BaseActivity(R.layout.activity_main), NavigationExecutor {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun startActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = ""

        window.statusBarColor = ContextCompat.getColor(this, com.rodionov.ui.R.color.colorPrimary)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        val navController = navHostFragment.navController
        initToolbar(navController)
        binding.mainBottomNavigation
            .setupWithNavController(navController)
    }

    private fun initToolbar(navController: NavController) {
        setSupportActionBar(binding.mainToolbar)
        setupActionBarWithNavController(navController = navController, AppBarConfiguration(navGraph = navController.graph, drawerLayout = null))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.mainContainer).navigateUp()
    }

}