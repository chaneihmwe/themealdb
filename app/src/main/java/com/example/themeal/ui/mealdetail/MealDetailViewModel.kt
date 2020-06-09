package com.example.themeal.ui.mealdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themeal.api.MealApi
import com.example.themeal.model.Meal
import com.example.themeal.model.MealDetail
import com.example.themeal.model.MealX
import com.example.themeal.model.MealXX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val mealDetail: MutableLiveData<List<MealXX>> = MutableLiveData()
    val loadError: MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getMealDetail(): LiveData<List<MealXX>> = mealDetail
    fun getLoadingError(): LiveData<Boolean> = loadError

    private val mealApi =  MealApi()

    fun loadMealDetail(mealID: String) {
        loading.value = true
        var apiCall = mealApi.getMealDetail(mealID)
        apiCall.enqueue(object : Callback<MealDetail> {
            override fun onFailure(call: Call<MealDetail>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<MealDetail>, response: Response<MealDetail>) {
                loadError.value = false
                var meals = response.body()?.meals ?: emptyList()
                mealDetail.value = meals
            }

        })
    }

}