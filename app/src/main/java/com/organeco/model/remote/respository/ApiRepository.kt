package com.organeco.model.remote.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.organeco.model.local.preferences.AuthenticationPreference
import com.organeco.model.remote.ApiService
import com.organeco.model.remote.response.LoginResponse
import com.organeco.model.remote.response.RegisterResponse
import com.organeco.model.remote.utils.MediatorResult
import com.organeco.view.utils.wrapperIdling

class ApiRepository(
    private val apiService: ApiService,
    private val preferences: AuthenticationPreference
) {

    suspend fun postLogin(
        email: String,
        password: String
    ): LiveData<MediatorResult<LoginResponse>> = wrapperIdling {
        liveData {
            emit(MediatorResult.Loading)
            try {
                val respon = apiService.postLogin(email, password)
                emit(MediatorResult.Success(respon))
            } catch (e: Exception) {
                emit(MediatorResult.Error(e.message.toString()))
            }
        }
    }

    suspend fun postRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<MediatorResult<RegisterResponse>> = wrapperIdling {
        liveData {
            emit(MediatorResult.Loading)
            try {
                val respon = apiService.postRegister(name, email, password)
                emit(MediatorResult.Success(respon))
            } catch (e: Exception) {
                emit(MediatorResult.Error(e.message.toString()))
            }
        }
    }

    fun getUserToken(): LiveData<String> = preferences.getTokenKey().asLiveData()
    fun getUserName(): LiveData<String> = preferences.getNameKey().asLiveData()
    fun getIdUser(): LiveData<String> = preferences.getUserId().asLiveData()
    fun getOnBoardStatus(): LiveData<Boolean> = preferences.getOnBoardStatus().asLiveData()

    suspend fun saveUser(
        onBoardStatus: Boolean,
        userName: String,
        tokenKey: String,
        userId: String
    ) {
        preferences.savePreferences(onBoardStatus, userName, tokenKey, userId)
    }
}