package com.example.themeal.api

import com.example.themeal.model.Meal
import com.example.themeal.model.MealDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealFilterInterface {

    @GET("filter.php")
    fun getMealByCategory(
        @Query("c") category: String
    ) : Call<Meal>

    @GET("lookup.php")
    fun getMealDetail(
        @Query("i") mealID: String
    ) : Call<MealDetail>
}