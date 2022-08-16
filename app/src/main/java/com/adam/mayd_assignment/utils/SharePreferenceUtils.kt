package com.adam.mayd_assignment.utils

import android.content.Context
import android.content.SharedPreferences
import com.adam.mayd_assignment.data.Shortly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Singleton

object SharePreferenceUtils {
    private val Pref_Name : String = "shortly"
    private val Key : String = "url"

    fun savePreference(context: Context, listOfShortedUrl: ArrayList<Shortly>) {
        val gson = Gson()
        val appSharedPrefs: SharedPreferences =
            context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
        val prefsEditor = appSharedPrefs.edit()
        val json: String = gson.toJson(listOfShortedUrl)
        prefsEditor.putString(Key, json)
        prefsEditor.apply()
    }

    fun readFromPreference(context: Context): ArrayList<Shortly> {
        val gson = Gson()
        val appSharedPrefs: SharedPreferences =
            context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
        return try {
            val json = appSharedPrefs.getString(Key, "")
            if (json?.isNotEmpty() == true)
            {
                val collectionType = object : TypeToken<Collection<Shortly?>?>() {}.type
                gson.fromJson(json,collectionType) as ArrayList<Shortly>
            }
            else
                arrayListOf()
        } catch (e: Exception) {
            print(e.printStackTrace())
            arrayListOf()
        }
    }
    fun clearSharePreference(context: Context){
        val appSharedPrefs: SharedPreferences =
            context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
        appSharedPrefs.edit().clear().apply()
    }

}