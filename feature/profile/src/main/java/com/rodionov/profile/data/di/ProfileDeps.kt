package com.rodionov.profile.data.di

import com.rodionov.database.dao.UserDao

interface ProfileDeps {

    val userDao: UserDao

}