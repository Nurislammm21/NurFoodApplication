package com.example.nurfoodapp.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nurfoodapp.activities.MealActivity
import com.example.nurfoodapp.data.Meal
import com.example.nurfoodapp.databinding.FragmentHomeBinding
import com.example.nurfoodapp.viewModel.HomeVIewModel


class HomeFragment : Fragment() {
private lateinit var binding : FragmentHomeBinding
private  val homeViewModel : HomeVIewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            // homeViewModel = ViewModelProvider(this)[HomeVIewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getRandomMeal()
        observerRandomMeal()
        onRandomClick()
    }

    private fun onRandomClick(){
        binding.randomMealCardView.setOnClickListener{
            startActivity(Intent(activity,MealActivity::class.java))
        }
    }


    private fun observerRandomMeal() {
            homeViewModel.observeRandomMealLivedata().observe(viewLifecycleOwner,object  : Observer<Meal>{
                override fun onChanged(value: Meal) {
                   Glide.with(this@HomeFragment).load(value.strMealThumb).into(binding.imageRandomMeal)
                }

            })

    }
}