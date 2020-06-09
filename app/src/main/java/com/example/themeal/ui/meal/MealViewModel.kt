package com.example.themeal.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themeal.api.MealApi
import com.example.themeal.api.MealFilterInterface
import com.example.themeal.model.Meal
import com.example.themeal.model.MealX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val mealByCategory: MutableLiveData<List<MealX>> = MutableLiveData()
    val loadError: MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getMealByCategory(): LiveData<List<MealX>> = mealByCategory
    fun getLoadingError(): LiveData<Boolean> = loadError

    private val mealApi =  MealApi()

    fun loadMeal(category: String) {
        loading.value = true
       var apiCall = mealApi.getMealByCategory(category)
        apiCall.enqueue(object : Callback<Meal>{
            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                loadError.value = false
                var meals = response.body()?.meals ?: emptyList()
                mealByCategory.value = meals
            }
        })
    }

}