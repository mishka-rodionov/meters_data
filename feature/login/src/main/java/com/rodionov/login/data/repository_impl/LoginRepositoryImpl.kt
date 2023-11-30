package com.rodionov.login.data.repository_impl

import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.UserDao
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.User
import com.rodionov.login.data.dto.Credentials
import com.rodionov.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    errorHandler: ErrorHandler
): BaseRepository(errorHandler), LoginRepository {

    override suspend fun getUser(
        credentials: Credentials,
        onSuccess: (User?) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess, onState) {
            userDao.getUserByCredentials(credentials.login)?.toModel()
        }
    }
}