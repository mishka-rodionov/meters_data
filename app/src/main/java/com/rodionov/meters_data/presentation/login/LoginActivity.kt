package com.rodionov.meters_data.presentation.login

import android.os.Bundle
import android.util.Log
import com.rodionov.base.platform.BaseActivity
import com.rodionov.meters_data.R

class LoginActivity: BaseActivity(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LOG_TAG", "onCreate: LoginActivity")
    }
}