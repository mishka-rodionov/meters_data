package com.rodionov.profile.data.di

import com.rodionov.database.dao.UserDao
import com.rodionov.utils.repositories.SharedPreferencesRepository

interface ProfileDeps {

    val userDao: UserDao
    val sharedPreferencesRepository: SharedPreferencesRepository

}