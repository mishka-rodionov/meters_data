package com.rodionov.login.di

import kotlin.properties.Delegates

object LoginDepsStore {

    var deps: LoginDeps by Delegates.notNull()

}