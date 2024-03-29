package com.rodionov.meters_data.data.app

import android.app.Application
import com.rodionov.login.di.LoginDepsStore
import com.rodionov.meter_creator.di.MeterCreatorDepsStore
import com.rodionov.meters_data.data.di.AppComponent
import com.rodionov.meters_data.data.di.DaggerAppComponent
import com.rodionov.profile.data.di.ProfileDepsStore

class MetersDataApp : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().context(applicationContext).build() }

    override fun onCreate() {
        super.onCreate()
        ProfileDepsStore.deps = appComponent
        MeterCreatorDepsStore.deps = appComponent
        LoginDepsStore.deps = appComponent
    }

}