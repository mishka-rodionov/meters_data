package com.rodionov.login.domain

import com.rodionov.base.state.State
import com.rodionov.domain.models.User

interface RegistrationRepository {

    suspend fun createNewUser(user:User, onSuccess:(User) -> Unit, onState: (State) -> Unit)

}