package com.example.promosee.model.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

class UserPreference private constructor(private val dataStore: DataStore<Preferences>)  {

    suspend fun saveUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.username
            preferences[TOKEN_KEY] = user.access_token
            preferences[USERID_KEY] = user.userid
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        val TOKEN_KEY = stringPreferencesKey("access_token")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val USERID_KEY = stringPreferencesKey("userid")


        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}