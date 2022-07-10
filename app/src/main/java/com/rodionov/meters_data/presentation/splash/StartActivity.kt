package com.rodionov.meters_data.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.rodionov.meters_data.presentation.login.LoginActivity
import com.rodionov.meters_data.presentation.main.MainActivity
import com.rodionov.utils.repositories.SharedPreferencesRepository

class StartActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        val prefs = SharedPreferencesRepository(applicationContext)
        if(!prefs.getUserId().isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this@StartActivity, LoginActivity::class.java))
        }
        finish()
    }
}