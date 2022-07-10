package com.rodionov.login.data.repository_impl

import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.UserDao
import com.rodionov.database.mappers.toEntity
import com.rodionov.domain.models.User
import com.rodionov.login.domain.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    errorHandler: ErrorHandler
) : BaseRepository(errorHandler), RegistrationRepository {

    override suspend fun createNewUser(
        user: User,
        onSuccess: (User) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess, onState) {
            userDao.setUser(user.toEntity())
            user
        }
    }
}