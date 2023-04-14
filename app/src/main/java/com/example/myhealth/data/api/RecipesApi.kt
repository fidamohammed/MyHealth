package com.example.myhealth.data.api

import com.example.myhealth.data.api.recipeCategories.RecipeCategoriesModel
import com.example.myhealth.data.api.recipeItem.RecipeModel
import com.example.myhealth.data.api.recipes.MealItemsModel
import com.example.myhealth.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {
    @GET(Constants.CATEGORIES_ENDPOINT)
    suspend fun getCategories(): RecipeCategoriesModel

    @GET(Constants.FILTER_ENDPOINT)
    suspend fun getFilteredResult(@Query("c") category: String): MealItemsModel

    @GET(Constants.RECIPE_ENDPOINT)
    suspend fun getRecipe(@Query("i")mealId: String): RecipeModel
}