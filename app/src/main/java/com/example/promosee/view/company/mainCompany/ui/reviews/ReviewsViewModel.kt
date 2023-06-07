package com.example.promosee.view.company.mainCompany.ui.reviews

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.promosee.model.remote.reponse.ReviewsResponse
import com.example.promosee.model.repository.CompanyRepository
import com.example.promosee.model.repository.InfluencerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import com.example.promosee.model.Result
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class ReviewsViewModel(
    private val companyRepository: CompanyRepository
    ): ViewModel() {

    private lateinit var username : String

    fun setUsername(Username : String){
        username = Username
    }

    fun getReviews() =  companyRepository.getReviews(username)


    // trying to be responsive
//    init{
//        getReviews()
//    }

    // setup username
//    private val _listReviews = MutableLiveData<Result<ReviewsResponse>>()
//    val listReviews: LiveData<Result<ReviewsResponse>> = _listReviews
//
//    fun setUsername(username: String) {
//        this.username = username
//        if (_listReviews.value == null) {
//            getReviews()
//        }
//    }
//
//    private fun getReviews() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = companyRepository.getReviews(username)
//            response.asFlow().collect {
//                _listReviews.postValue(it)
//            }
//        }
//    }
//
//    // Save and restore the username value during configuration changes
//    fun onSaveInstanceState(outState: Bundle) {
//        outState.putString("username", username)
//    }
//
//    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        username = savedInstanceState?.getString("username") ?: ""
//    }



}