package com.msk.besinleruygulamas.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class ozelSharedPreferences {
    private val time="time"
    companion object{
        private var sharedPreferences:SharedPreferences?=null

        @Volatile private var instance:ozelSharedPreferences?=null

        private val lock=Any()
        operator fun invoke(context: Context):ozelSharedPreferences= instance ?: synchronized(lock){
        instance ?: OzelSharedPreferencesYap(context).also {

        }
        }
        private fun OzelSharedPreferencesYap(context: Context):ozelSharedPreferences{
            sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return ozelSharedPreferences()
        }
    }

    fun ZamanKaydet(zaman:Long){
        sharedPreferences?.edit(commit=true){
            putLong(time,zaman)
        }
    }
    fun zamani_al()= sharedPreferences?.getLong(time,0)
}