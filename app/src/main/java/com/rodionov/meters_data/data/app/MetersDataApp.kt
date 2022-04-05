package com.rodionov.meters_data.data.app

import android.app.Application
import com.rodionov.meters_data.data.di.ApplicationComponent
import com.rodionov.meters_data.data.di.DaggerApplicationComponent

class MetersDataApp: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }

}