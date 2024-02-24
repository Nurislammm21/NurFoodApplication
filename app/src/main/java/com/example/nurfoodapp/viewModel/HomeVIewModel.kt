package com.example.nurfoodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nurfoodapp.data.CategoryList
import com.example.nurfoodapp.data.CategoryMeals
import com.example.nurfoodapp.data.Meal
import com.example.nurfoodapp.data.MealList
import com.example.nurfoodapp.network.MealApiData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeVIewModel() : ViewModel() {
    private  var randomMealLiveData = MutableLiveData<Meal>()
    private var popularMealLiveData = MutableLiveData<List<CategoryMeals>>()

    fun getRandomMeal(){
        MealApiData.mealApi.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e("HomeFragment: ", t.message.toString())
            }

        })
    }

    fun getPopularItem(){
        MealApiData.mealApi.getPopularItems("Seafood").enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.body() != null){
                    popularMealLiveData.value = response.body()!!.meals

                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("HomeFragment: ", t.message.toString())
            }

        })
    }

    fun observeRandomMealLivedata() : LiveData<Meal>{
        return randomMealLiveData
    }

    fun observePopularMealLivedata() : LiveData<List<CategoryMeals>>{
        return popularMealLiveData
    }

}