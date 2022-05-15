package com.rodionov.profile.domain.repository

import com.rodionov.domain.models.User

interface ProfileRepository {

    suspend fun setPersonalInformation(user: User)
    suspend fun getUser(): User

}