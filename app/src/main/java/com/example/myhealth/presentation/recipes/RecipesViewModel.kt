package com.example.myhealth.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.domain.repository.RecipeRepository
import com.example.myhealth.util.ResultStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(val repository: RecipeRepository): ViewModel() {
    private var _categories: MutableStateFlow<ResultStates> = MutableStateFlow(ResultStates.Loading)
    val categories: StateFlow<ResultStates> get() = _categories

    private var _meals: MutableStateFlow<ResultStates> = MutableStateFlow(ResultStates.Loading)
    val meals: StateFlow<ResultStates> get() = _meals

    private var _recipe: MutableStateFlow<ResultStates> = MutableStateFlow(ResultStates.Loading)
    val recipe: StateFlow<ResultStates> get() = _recipe

    var currentCategory: String? = null
    var currentMeal: String? = null

    fun getCategories(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val apiResult = repository.getCategories()
                if(apiResult.categories.isEmpty()){
                    _categories.value = ResultStates.Error("Empty Response")
                }else{
                    _categories.value = ResultStates.Success(apiResult.categories)
                }
            }catch (e: Exception){
                _categories.value = ResultStates.Error("Something went wrong: ${e.message}")
            }
        }
    }

    fun getMealsForCategory(category: String){
        viewModelScope.launch {
            try{
                val apiResult = repository.getMeals(category)
                if(apiResult.meals.isEmpty()){
                    _meals.value = ResultStates.Error("Empty Response")
                }else{
                    _meals.value = ResultStates.Success(apiResult.meals)
                }
            }catch (e: Exception){
                _meals.value = ResultStates.Error("Something went wrong: ${e.message}")
            }
        }
    }

    fun getMealRecipe(mealId: String){
        viewModelScope.launch {
            try{
                val apiResult = repository.getRecipe(mealId)
                if(apiResult.meals.isEmpty()){
                    _recipe.value = ResultStates.Error("Empty Response")
                }else{
                    apiResult.meals.forEach {
                        _recipe.value = ResultStates.Success(it.toMealModel())
                    }
                }
            }catch (e: Exception){
                _recipe.value = ResultStates.Error("Something went wrong: ${e.message}")
            }
        }
    }
}