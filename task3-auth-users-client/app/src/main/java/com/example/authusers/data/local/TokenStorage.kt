package com.example.authusers.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val tokenKey = stringPreferencesKey("access_token")

    val token: Flow<String?> = dataStore.data.map { it[tokenKey] }

    suspend fun saveToken(value: String) {
        dataStore.edit { it[tokenKey] = value }
    }

    suspend fun clearToken() {
        dataStore.edit { it.remove(tokenKey) }
    }
}
