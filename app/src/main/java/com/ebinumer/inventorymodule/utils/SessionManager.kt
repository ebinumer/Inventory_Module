package com.ebinumer.inventorymodule.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SessionManager @SuppressLint("CommitPrefEdits") constructor(private val context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val USERNAME = "username"

    /*App open status*/
    var appOpenStatus: Boolean
        get() = pref.getBoolean(APP_OPEN_STATUS, false)
        set(value) {
            editor.putBoolean(APP_OPEN_STATUS, value).apply()
        }
    init {
        // Shared pref mode
        val PRIVATE_MODE = 0
        pref = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        editor = pref.edit()
    }
    fun logout(){
        editor.clear()
        editor.commit()
    }

    companion object {
        private const val PREF_NAME = "room_app"
        private val APP_OPEN_STATUS = "appOpenStatus"
    }
}