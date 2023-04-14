package com.example.myhealth.data.api.recipeCategories


import com.google.gson.annotations.SerializedName

data class RecipeCategoriesModel(
    @SerializedName("categories")
    val categories: List<CategoryModel>
)