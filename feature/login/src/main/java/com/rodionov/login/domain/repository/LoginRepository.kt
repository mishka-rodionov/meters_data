package com.rodionov.login.domain.repository

import com.rodionov.base.state.State
import com.rodionov.domain.models.User
import com.rodionov.login.data.dto.Credentials

interface LoginRepository {

    suspend fun getUser(credentials: Credentials, onSuccess:(User?) -> Unit, onState: (State) -> Unit)

}