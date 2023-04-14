package com.example.myhealth.domain.repository

import com.example.myhealth.data.api.recipeCategories.RecipeCategoriesModel
import com.example.myhealth.data.api.recipeItem.RecipeModel
import com.example.myhealth.data.api.recipes.MealItemsModel

interface RecipeRepository {
    suspend fun getCategories(): RecipeCategoriesModel
    suspend fun getMeals(category: String): MealItemsModel
    suspend fun getRecipe(id: String): RecipeModel
}