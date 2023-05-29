package com.organeco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.organeco.model.local.preferences.repository.PreferenceRepository
import kotlinx.coroutines.launch

class UserPreferencesVM(private val PreferenceRepository : PreferenceRepository
): ViewModel() {

    fun getUserName(): LiveData<String> = PreferenceRepository.getUserName()
    fun getTokenKey(): LiveData<String> = PreferenceRepository.getTokenKey()
    fun getUserId(): LiveData<String> = PreferenceRepository.getUserIdKey()

    fun saveUserPreferences(userName: String, pass: String, tokenKey: String, userId: String){
        viewModelScope.launch {
            PreferenceRepository.savePreferences(userName, tokenKey, userId)
        }
    }
}