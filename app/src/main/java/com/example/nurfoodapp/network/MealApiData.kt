package com.example.nurfoodapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealApiData {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val mealApi: MealApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(MealApi::class.java)
    }

}