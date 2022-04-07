package com.rodionov.profile.data.repository_impl

import com.rodionov.database.dao.UserDao
import com.rodionov.database.mappers.toEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.User
import com.rodionov.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): ProfileRepository {

    override suspend fun setPersonalInformation(user: User) {
        userDao.setUser(userEntity = user.toEntity())
    }

    override suspend fun getUser(): User = userDao.getUser().toModel()
}