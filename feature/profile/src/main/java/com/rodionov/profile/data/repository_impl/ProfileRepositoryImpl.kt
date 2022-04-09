package com.rodionov.profile.data.repository_impl

import android.util.Log
import com.rodionov.database.dao.UserDao
import com.rodionov.database.mappers.toEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.User
import com.rodionov.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl /*@Inject*/ constructor(
    private val userDao: UserDao
): ProfileRepository {

    override suspend fun setPersonalInformation(user: User) {
        userDao.setUser(userEntity = user.toEntity())
    }

    override suspend fun getUser(): User {
        val user = userDao.getUser()
        return if (user == null) {
            Log.d("LOG_TAG", "getUser: null")
            val newUser = User(id = "123", firstName = "Ivan", lastName = "Ivanovich", email = "ivan@mail.ru", phone = "+79271234567")
            userDao.setUser(newUser.toEntity())
            newUser
        } else {
            Log.d("LOG_TAG", "getUser: not null")
            user.toModel()
        }
    }
}