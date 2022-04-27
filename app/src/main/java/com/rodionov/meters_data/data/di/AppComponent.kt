package com.rodionov.meters_data.data.di

import android.content.Context
import com.rodionov.database.dao.UserDao
import com.rodionov.profile.data.factory.ViewModelFactory
import com.rodionov.profile.data.di.ProfileDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent : ProfileDeps{

    override val userDao: UserDao

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent

    }
}

@Module
class AppModule