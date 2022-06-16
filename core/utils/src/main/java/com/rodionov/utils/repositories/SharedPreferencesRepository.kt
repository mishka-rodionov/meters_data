package com.rodionov.utils.repositories

import android.content.Context
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val context: Context
) {

    val sharedPreferences = context.getSharedPreferences(SHARED_FILE_NAME, Context.MODE_PRIVATE)

    fun putUserId(usedId: String) {
        with(sharedPreferences.edit()) {
            putString(USER_ID, usedId)
            apply()
        }
    }

    fun getUserId() = sharedPreferences.getString(USER_ID, "")

    companion object {
        const val SHARED_FILE_NAME = "com.rodionov.SHARED_FILE_NAME"
        const val USER_ID = "USER_ID"
    }

}