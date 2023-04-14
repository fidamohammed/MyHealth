package com.example.myhealth.data.repository

import com.example.myhealth.data.api.recipeCategories.RecipeCategoriesModel
import com.example.myhealth.data.api.RecipesApi
import com.example.myhealth.data.api.recipeItem.RecipeModel
import com.example.myhealth.data.api.recipes.MealItemsModel
import com.example.myhealth.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(val api: RecipesApi): RecipeRepository {
    override suspend fun getCategories(): RecipeCategoriesModel = api.getCategories()
    override suspend fun getMeals(category: String): MealItemsModel = api.getFilteredResult(category)
    override suspend fun getRecipe(id: String): RecipeModel = api.getRecipe(id)

}