package com.rodionov.profile.data.di

import kotlin.properties.Delegates

object ProfileDepsStore {

    var deps: ProfileDeps by Delegates.notNull()

}