package com.example.showdialog

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs( context: Context) {

    private val sharedPrefFile = "thefile"

    private var mPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
    val editor = mPreferences.edit()
    val NAME = "dialog"

    fun showdialog(boolean: Boolean) {
        editor.putBoolean(NAME, boolean)
        editor.apply()
        editor.commit()
    }

    fun shouldShowDialog() = mPreferences.getBoolean(NAME, true)
}
