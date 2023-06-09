package com.organeco.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.organeco.di.Injection
import com.organeco.model.remote.respository.ApiRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val repository: ApiRepository,
    private val repositoryMl: ApiRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UserPreferencesVM::class.java) -> {
                UserPreferencesVM(repository) as T
            }
            modelClass.isAssignableFrom(CalculatorViewModel::class.java) -> {
                CalculatorViewModel(repositoryMl) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    Injection.provideRepositoryMl(context)
                )
            }.also { instance = it }
    }
}