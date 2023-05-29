package com.organeco.di

import android.content.Context
import com.organeco.model.local.preferences.AuthenticationPreference
import com.organeco.model.local.preferences.dataStore
import com.organeco.model.local.preferences.repository.PreferenceRepository

object Injection {
    fun provideRepository(context: Context) : PreferenceRepository {
        return PreferenceRepository(
            AuthenticationPreference.getInstance(context.dataStore)
        )
    }
}