package com.example.myhealth.presentation.recipes.recipeItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealth.R
import com.example.myhealth.data.api.recipes.MealModel
import com.example.myhealth.databinding.FragmentRecipeItemsBinding
import com.example.myhealth.presentation.recipes.RecipesViewModel
import com.example.myhealth.util.ResultStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeItemsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeItemsBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeItemsBinding.inflate(layoutInflater)
        val recipeViewModel: RecipesViewModel by activityViewModels()

        recyclerView = binding.rvAllRecipes
        recyclerView.layoutManager = GridLayoutManager(context,2)


        val category = recipeViewModel.currentCategory
        if (category != null) {
            recipeViewModel.getMealsForCategory(category)
            recipeViewModel.meals.asLiveData().observe(viewLifecycleOwner){state->
                when(state){
                    is ResultStates.Success<*> -> {
                        recyclerView.adapter = MealsAdapter(state.data as List<MealModel>){
                            recipeViewModel.currentMeal = it.idMeal
                            findNavController().navigate(R.id.action_recipeItemsFragment_to_mealRecipeFragment)
                        }
                    }
                    is ResultStates.Error -> {
                        Toast.makeText(context,"${state.error} ", Toast.LENGTH_SHORT).show()
                    }
                    is ResultStates.Loading -> {

                    }
                }

            }
        }
        return binding.root
    }


}