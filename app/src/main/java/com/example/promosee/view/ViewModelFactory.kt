package com.example.promosee.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.injection.Injection
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.AuthRepository
import com.example.promosee.view.company.mainCompany.ui.home.HomeViewModel
import com.example.promosee.view.login.LoginViewModel
import com.example.promosee.view.register.RegisterViewModel
import com.example.promosee.view.splash.SplashViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val preference: UserPreference
) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(authRepository) as T
            }else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(authRepository) as T
            }else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                return SplashViewModel(preference) as T
            }
            else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(preference) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.authRepository(context),
                    Injection.providePreferences(context)
                )
            }.also { instance = it }
    }
}
