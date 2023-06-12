package com.example.promosee.view.company.mainCompany.ui.reviews

import androidx.lifecycle.ViewModel
import com.example.promosee.model.remote.request.UpdateOrderRequest
import com.example.promosee.model.repository.CompanyRepository

class ReviewsViewModel(
    private val companyRepository: CompanyRepository
    ): ViewModel() {

    private lateinit var username : String
    private lateinit var rating : String
    private lateinit var comment : String
    private lateinit var orderId : String

    fun setUsername(Username : String){
        username = Username
    }

    fun setReview(Rating: String, Comment: String, OrderId: String){
        rating = Rating
        comment = Comment
        orderId = OrderId
    }

    fun getReviews() =  companyRepository.getReviews(username)

    fun postReview() = companyRepository.PostReview(rating.toInt(),comment,orderId)

    fun updateOrder(update_data: UpdateOrderRequest, order_id: String) =  companyRepository.updateOrder(update_data, order_id)


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