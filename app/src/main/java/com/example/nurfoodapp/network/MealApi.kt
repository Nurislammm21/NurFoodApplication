package com.example.nurfoodapp.network


import com.example.nurfoodapp.data.Meal
import com.example.nurfoodapp.data.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {


    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}