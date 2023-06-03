package com.example.promosee.model.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>)  {

    suspend fun saveUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.username
            preferences[TOKEN_KEY] = user.access_token
            preferences[USERID_KEY] = user.userid
            preferences[USER_ACCESS] = user.user_access
        }
    }

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[USERNAME_KEY] ?: "",
                preferences[USERID_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[USER_ACCESS] ?: ""
            )
        }
    }

    suspend fun removeUser(){
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = ""
            preferences[TOKEN_KEY] = ""
            preferences[USERID_KEY] = ""
            preferences[USER_ACCESS] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        val TOKEN_KEY = stringPreferencesKey("access_token")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val USERID_KEY = stringPreferencesKey("userid")
        private val USER_ACCESS = stringPreferencesKey("user_type")


        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}