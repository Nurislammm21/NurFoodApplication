package com.example.nurfoodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.nurfoodapp.R
import com.example.nurfoodapp.data.Meal
import com.example.nurfoodapp.databinding.ActivityMealBinding
import com.example.nurfoodapp.fragments.HomeFragment
import com.example.nurfoodapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private val mealViewModel: MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMealInformationFromIntent()
        setInformationInViews()
       // mealViewModel.getMealDetail(mealId)
       // observeMealLiveData()
        onYoutubeImageClick()

    }
    private fun onYoutubeImageClick(){
        binding.imageYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yHNDecWQR54"))
            startActivity(intent)
        }
    }

    private fun observeMealLiveData(){
        mealViewModel.observerMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                value.let { meal ->
                    binding.txtCategory.text = "Category : ${meal.strCategory}"
                    binding.txtArea.text = "Area : ${meal.strArea}"
                    binding.txtInstructionsStep.text = meal.strInstructions
                }
            }

        })
    }

    private fun setInformationInViews(){
        Glide.with(applicationContext).load(mealThumb).into(binding.imageMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }


    private fun getMealInformationFromIntent(){
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }






}