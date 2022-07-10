package com.rodionov.meters_data.presentation.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.rodionov.base.platform.BaseActivity
import com.rodionov.login.presentation.login.LoginFragment
import com.rodionov.meters_data.R
import com.rodionov.meters_data.presentation.login.LoginActivity
import com.rodionov.meters_data.presentation.main.MainActivity
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
        splashScreen.setKeepOnScreenCondition { true }
        val prefs = SharedPreferencesRepository(applicationContext)
        Log.d("LOG_TAG", "onCreate: user id ${prefs.getUserId()}.")
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@StartActivity, LoginActivity::class.java))
            finish()
        }
        if(!prefs.getUserId().isNullOrEmpty()) {
            Log.d("LOG_TAG", "onCreate: true ${prefs.getUserId() != null}")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Log.d("LOG_TAG", "onCreate: false ${prefs.getUserId() != null}")
            startActivity(Intent(this@StartActivity, LoginActivity::class.java))
            finish()
        }
//        finish()
    }
}