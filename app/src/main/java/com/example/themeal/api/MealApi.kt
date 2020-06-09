package com.example.themeal.api

import com.example.themeal.model.Meal
import com.example.themeal.model.MealDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealApi {

    private val mealFilterInterface: MealFilterInterface

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mealFilterInterface = retrofit.create(MealFilterInterface::class.java)
    }

    fun getMealByCategory(category: String) : Call<Meal> {
        return mealFilterInterface.getMealByCategory(category)
    }

    fun getMealDetail(mealID: String) : Call<MealDetail> {
        return mealFilterInterface.getMealDetail(mealID)
    }

}