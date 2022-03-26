package com.rodionov.meters_data.data.app

import android.app.Application
import com.rodionov.meters_data.data.di.AppComponent
import com.rodionov.meters_data.data.di.DaggerAppComponent

class MetersDataApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}