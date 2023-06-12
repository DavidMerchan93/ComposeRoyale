package com.david.composeroyal.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.david.composeroyal.data.api.common.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceSettings(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_DATABASE)

    private val _accessToken = stringPreferencesKey(ACCESS_TOKEN)

    val accessToken: Flow<String> = context.dataStore.data.map { preferences ->
        "${Constants.BEARER} ${preferences[_accessToken]}"
    }

    suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { settings ->
            settings[_accessToken] = token
        }
    }

    companion object {
        private const val SETTINGS_DATABASE = "settings_database"
        private const val ACCESS_TOKEN = "access_token"
    }
}
