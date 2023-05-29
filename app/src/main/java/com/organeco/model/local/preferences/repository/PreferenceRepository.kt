package com.organeco.model.local.preferences.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.organeco.model.local.preferences.AuthenticationPreference

class PreferenceRepository(private val preferences : AuthenticationPreference) {

    fun getUserName(): LiveData<String> = preferences.getNameKey().asLiveData()

    fun getTokenKey(): LiveData<String> = preferences.getTokenKey().asLiveData()

    fun getUserIdKey(): LiveData<String> = preferences.getUserId().asLiveData()

    suspend fun savePreferences(userName: String, pass: String, token: String) {
        preferences.savePreferences(userName, pass, token)
    }
}