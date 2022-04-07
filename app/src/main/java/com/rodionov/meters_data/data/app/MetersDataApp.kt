package com.rodionov.meters_data.data.app

import android.app.Application
import com.rodionov.meters_data.data.di.AppComponent
import com.rodionov.meters_data.data.di.DaggerAppComponent

class MetersDataApp : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().context(applicationContext).build() }

    override fun onCreate() {
        super.onCreate()
    }

}