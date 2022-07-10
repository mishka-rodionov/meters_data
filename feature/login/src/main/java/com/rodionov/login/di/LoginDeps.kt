package com.rodionov.login.di

import com.rodionov.database.dao.UserDao
import com.rodionov.utils.repositories.SharedPreferencesRepository

interface LoginDeps {

    val userDao: UserDao
    val preferencesRepository: SharedPreferencesRepository

}