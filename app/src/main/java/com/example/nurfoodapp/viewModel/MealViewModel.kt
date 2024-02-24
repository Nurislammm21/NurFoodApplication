package com.example.nurfoodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nurfoodapp.data.Meal
import com.example.nurfoodapp.data.MealList
import com.example.nurfoodapp.network.MealApiData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MealViewModel() : ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id : String){
        MealApiData.mealApi.getMealDetails(id).enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.isSuccessful && response.body() != null && response.body()!!.meals.isNotEmpty()){
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }else
                    return

            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e("MainActivity: ", t.message.toString())
            }
        })
    }

    fun observerMealDetailsLiveData(): LiveData<Meal>{
       return mealDetailsLiveData
    }
}