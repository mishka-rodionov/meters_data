package com.rodionov.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(list: String?): List<String> {
        return if (list.isNullOrEmpty()) {
            emptyList()
        } else {
            Gson().fromJson(if (list == "null") "[]" else list, Array<String>::class.java)
                .toList()
        }
    }

}