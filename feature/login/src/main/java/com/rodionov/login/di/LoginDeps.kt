package com.rodionov.login.di

import com.rodionov.base.state.ErrorHandler
import com.rodionov.database.dao.UserDao
import com.rodionov.utils.repositories.SharedPreferencesRepository

interface LoginDeps {

    val userDao: UserDao
    val errorHandler: ErrorHandler
    val preferencesRepository: SharedPreferencesRepository

}