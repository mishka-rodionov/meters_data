package com.rodionov.meters_data.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.rodionov.base.interaction.NavigationExecutor
import com.rodionov.base.platform.BaseActivity
import com.rodionov.meters_data.R
import com.rodionov.meters_data.presentation.main.MainActivity

class LoginActivity: BaseActivity(R.layout.activity_login), NavigationExecutor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LOG_TAG", "onCreate: LoginActivity")
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.loginContainer) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun startActivity() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        } )
        finish()
    }

}