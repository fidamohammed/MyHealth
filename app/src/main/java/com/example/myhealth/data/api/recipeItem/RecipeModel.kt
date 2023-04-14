package com.example.myhealth.data.api.recipeItem


import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("meals")
    val meals: List<MealDto>
)