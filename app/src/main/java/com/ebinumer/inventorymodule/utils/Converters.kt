package com.ebinumer.inventorymodule.utils

import androidx.room.TypeConverter
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<GrnItemsEntity>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<GrnItemsEntity>::class.java).toList()
}