package com.rodionov.login.di

import kotlin.properties.Delegates

object LoginDepsStore {

    val loginDeps: LoginDeps by Delegates.notNull()

}