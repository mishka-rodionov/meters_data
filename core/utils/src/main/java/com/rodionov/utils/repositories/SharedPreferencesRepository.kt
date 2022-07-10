package com.rodionov.utils.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val context: Context
) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_FILE_NAME, Context.MODE_PRIVATE)

    fun putUserId(usedId: String) {
        with(sharedPreferences.edit()) {
            putString(USER_ID, usedId)
            apply()
        }
    }

    fun getUserId(): String? {
        val userId = sharedPreferences.getString(USER_ID, "")
        Log.d("LOG_TAG", "getUserId: $userId")
        return userId
    }

    companion object {
        const val SHARED_FILE_NAME = "com.rodionov.SHARED_FILE_NAME"
        const val USER_ID = "USER_ID"
    }

}