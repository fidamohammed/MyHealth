package com.example.myhealth.presentation.recipes.mealRecipe

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myhealth.R
import com.example.myhealth.data.api.recipeItem.MealModel
import com.example.myhealth.databinding.FragmentMealRecipeBinding
import com.example.myhealth.presentation.recipes.RecipesViewModel
import com.example.myhealth.util.ResultStates


class MealRecipeFragment : Fragment() {
   private lateinit var binding: FragmentMealRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealRecipeBinding.inflate(layoutInflater)

        val recipesViewModel: RecipesViewModel by activityViewModels()

        val mealId = recipesViewModel.currentMeal
        if (mealId != null) {
            recipesViewModel.getMealRecipe(mealId)
        }
        binding.detailBody.tvLink.movementMethod = LinkMovementMethod.getInstance()
        recipesViewModel.recipe.asLiveData().observe(viewLifecycleOwner){ state ->
            when(state){
                is ResultStates.Success<*> -> {
                    val meal = state.data as MealModel
                    binding.detailHeader.tvMealName.text = meal.strMeal
                    binding.detailBody.tvArea.text = meal.strArea
                    binding.detailBody.tvCategory.text = meal.strCategory
                    binding.detailBody.tvInstruction.text = meal.strInstructions
                    binding.detailBody.tvLink.text = meal.strYoutube
                    Glide.with(this).load(meal.strMealThumb).apply(
                        RequestOptions().placeholder(R.drawable.recipe)
                    ).into(binding.detailHeader.ivMealItem)
                }
                is ResultStates.Error -> {

                }
                ResultStates.Loading -> {

                }
            }
        }

        return binding.root
    }


}