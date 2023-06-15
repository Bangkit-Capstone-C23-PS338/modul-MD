package com.example.promosee.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.injection.Injection
import com.example.promosee.model.local.preference.UserPreference
import com.example.promosee.model.repository.AuthRepository
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailViewModel
import com.example.promosee.view.company.mainCompany.ui.home.HomeViewModel
import com.example.promosee.view.company.mainCompany.ui.notifications.NotificationsViewModel
import com.example.promosee.view.company.mainCompany.ui.order.OrderDetailViewModel
import com.example.promosee.view.company.mainCompany.ui.order.OrderViewModel
import com.example.promosee.view.company.mainCompany.ui.profile.ProfileViewModel
import com.example.promosee.view.company.mainCompany.ui.reviews.ReviewsViewModel
import com.example.promosee.view.company.mainCompany.ui.search.SearchViewModel
import com.example.promosee.view.influencer.mainInflu.ui.home.HomeInfluencerViewModel
import com.example.promosee.view.influencer.mainInflu.ui.notifications.NotificationsInfluencerViewModel
import com.example.promosee.view.influencer.mainInflu.ui.product.ProductViewModel
import com.example.promosee.view.influencer.mainInflu.ui.profile.ProfileInfluencerViewModel
import com.example.promosee.view.login.LoginViewModel
import com.example.promosee.view.register.RegisterViewModel
import com.example.promosee.view.splash.SplashViewModel

class ViewModelFactory(
    private val authRepository: AuthRepository,
    private val preference: UserPreference,
    private val companyRepository: CompanyRepository,
    private val influencerRepository: InfluencerRepository
) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(authRepository) as T
            }else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(authRepository) as T
            }else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                return SplashViewModel(preference,authRepository) as T
            }else if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(companyRepository) as T
            }else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(preference, companyRepository) as T
            }else if (modelClass.isAssignableFrom(InfluencerDetailViewModel::class.java)) {
                return InfluencerDetailViewModel(companyRepository) as T
            }else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(authRepository,companyRepository) as T
            }else if (modelClass.isAssignableFrom(ProfileInfluencerViewModel::class.java)) {
                return ProfileInfluencerViewModel(authRepository,influencerRepository) as T
            }else if (modelClass.isAssignableFrom(ReviewsViewModel::class.java)) {
                return ReviewsViewModel(companyRepository) as T
            }else if (modelClass.isAssignableFrom(HomeInfluencerViewModel::class.java)) {
                return HomeInfluencerViewModel(influencerRepository) as T
            }else if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
                return OrderViewModel(preference, companyRepository) as T
            }else if (modelClass.isAssignableFrom(OrderDetailViewModel::class.java)) {
                return OrderDetailViewModel(preference, companyRepository) as T
            }else if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                return ProductViewModel(influencerRepository) as T
            }else if (modelClass.isAssignableFrom(NotificationsViewModel::class.java)) {
                return NotificationsViewModel(preference, companyRepository) as T
            } else if (modelClass.isAssignableFrom(NotificationsInfluencerViewModel::class.java)) {
                return NotificationsInfluencerViewModel(preference, influencerRepository) as T
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
                    Injection.providePreferences(context),
                    Injection.companyRepository(context),
                    Injection.influencerRepository(context)
                )
            }.also { instance = it }
    }
}
