package com.organeco.model.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthenticationPreference private constructor(private val dataStore: DataStore<Preferences>){
    private val onBoardKey = booleanPreferencesKey("onBoard")
    private val nameKey = stringPreferencesKey("NameKey")
    private val tokenKey = stringPreferencesKey("UserToken")
    private val userIdKey = stringPreferencesKey("userId_key")

    fun getOnBoardKey(): Flow<Boolean>{
        return dataStore.data.map {
            it[onBoardKey] ?: false
        }
    }

    fun getNameKey(): Flow<String>{
        return dataStore.data.map {
            it[nameKey] ?: ""
        }
    }

    fun getTokenKey(): Flow<String>{
        return dataStore.data.map {
            it[tokenKey] ?: ""
        }
    }

    fun getUserId(): Flow<String>{
        return dataStore.data.map {
            it[userIdKey] ?: ""
        }
    }

    suspend fun savePreferences(onBoard : Boolean, name : String, tokenId:String, userId : String){
        dataStore.edit {
            it[onBoardKey] = onBoard
            it[nameKey] = name
            it[tokenKey] = tokenId
            it[userIdKey] = userId
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: AuthenticationPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>):AuthenticationPreference{
            return INSTANCE ?: synchronized(this){
                val instance = AuthenticationPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }


}