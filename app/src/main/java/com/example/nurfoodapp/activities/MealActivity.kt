package com.example.nurfoodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nurfoodapp.R
import com.example.nurfoodapp.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}