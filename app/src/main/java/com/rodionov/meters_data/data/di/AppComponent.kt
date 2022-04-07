package com.rodionov.meters_data.data.di

import android.content.Context
import androidx.room.Room
import com.rodionov.database.MetersDataDatabase
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent

    }
}

@Module
class AppModule {



}