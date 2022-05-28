package com.rodionov.meter_creator.di

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rodionov.domain.models.Meter
import kotlin.properties.Delegates

class CreatorViewModel: ViewModel() {

    init {
        Log.d("LOG_TAG", "init: CreatorViewModel has been created")
    }

    val creatorComponent = DaggerMeterCreatorComponent.builder().deps(MeterCreatorDepsStore.deps).build()
    var meter: Meter by Delegates.notNull()

    override fun onCleared() {
        Log.d("LOG_TAG", "onCleared: CreatorViewModel has been cleared")
        super.onCleared()
    }

}