package com.rodionov.login.di

import com.rodionov.database.dao.UserDao

interface LoginDeps {

    val userDao: UserDao

}