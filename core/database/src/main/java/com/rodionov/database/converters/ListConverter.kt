package com.rodionov.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun fromPallets(pallets: List<String>): String {
        val gson = Gson()
        return gson.toJson(pallets)
    }

    @TypeConverter
    fun toPallets(pallets: String): List<String> {
        return Gson().fromJson(pallets, Array<String>::class.java).toList()
    }

}