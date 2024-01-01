package com.rodionov.meters_data.data.di

import android.content.Context
import com.rodionov.base.state.ErrorHandler
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterDataDao
import com.rodionov.database.dao.MeterInfoDao
import com.rodionov.database.dao.UserDao
import com.rodionov.login.di.LoginDeps
import com.rodionov.meter_creator.di.MeterCreatorDeps
import com.rodionov.meter_data_input.di.MeterDataInputDeps
import com.rodionov.meters_data.presentation.main.MainActivity
import com.rodionov.profile.data.factory.ViewModelFactory
import com.rodionov.profile.data.di.ProfileDeps
import com.rodionov.utils.repositories.SharedPreferencesRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class, DatabaseModule::class, MainViewModelModule::class])
interface AppComponent : ProfileDeps, MeterCreatorDeps, LoginDeps, MeterDataInputDeps {

    override val userDao: UserDao
    override val meterDao: MeterDao
    override val flatDao: FlatDao
    override val meterInfoDao: MeterInfoDao
    override val meterDataDao: MeterDataDao

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent

    }
}

@Module
class AppModule {

    @Provides
    fun provideErrorHandler() = ErrorHandler()

    @Provides
    fun provideSharedPreferences(context: Context) = SharedPreferencesRepository(context)

}