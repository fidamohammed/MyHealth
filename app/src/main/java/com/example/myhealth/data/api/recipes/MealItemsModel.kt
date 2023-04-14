package com.example.myhealth.data.api.recipes


import com.google.gson.annotations.SerializedName

data class MealItemsModel(
    @SerializedName("meals")
    val meals: List<MealModel>
)