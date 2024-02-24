package com.example.nurfoodapp.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nurfoodapp.activities.MealActivity
import com.example.nurfoodapp.adapters.MostPopularAdapter
import com.example.nurfoodapp.data.CategoryMeals
import com.example.nurfoodapp.data.Meal
import com.example.nurfoodapp.databinding.FragmentHomeBinding
import com.example.nurfoodapp.viewModel.HomeVIewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeVIewModel by viewModels()
    private lateinit var randomMeal : Meal
    private lateinit var adapter : MostPopularAdapter
  //  private lateinit var popularMeal : CategoryMeals


    companion object{
            const val MEAL_ID = "com.example.nurfoodapp.fragments"
            const val MEAL_NAME = "com.example.nurfoodapp.fragments"
            const val MEAL_THUMB = "com.example.nurfoodapp.fragments"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            // homeViewModel = ViewModelProvider(this)[HomeVIewModel::class.java]
        adapter = MostPopularAdapter()
        // if code work wrong check this adapter because you have fixed and you are just experimenting remind all
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

        preparePopularRecyclerView()

        homeViewModel.getRandomMeal()
        observerRandomMeal()
        onRandomClick()

        homeViewModel.getPopularItem()
        observePopularLiveData()


        onItemPopularClick()
    }


    private fun onItemPopularClick(){
        adapter.onItemClick = {meal ->
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)

        }

    }

    private fun preparePopularRecyclerView() = with(binding){
        rcViewFoodPopular.adapter = adapter
        rcViewFoodPopular.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

    }

    private fun observePopularLiveData(){
        homeViewModel.observePopularMealLivedata().observe(viewLifecycleOwner
        ) {mealList ->
            adapter.setMeals(mealsList = mealList as ArrayList<CategoryMeals>)

        }
    }


    private fun onRandomClick(){
        binding.randomMealCardView.setOnClickListener{
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }


    private fun observerRandomMeal() {
            homeViewModel.observeRandomMealLivedata().observe(viewLifecycleOwner) { meal ->
                Glide.with(this@HomeFragment).load(meal!!.strMealThumb)
                    .into(binding.imageRandomMeal)

                this.randomMeal = meal
            }

    }


}