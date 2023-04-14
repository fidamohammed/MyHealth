package com.example.myhealth.presentation.recipes.recipeItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myhealth.R
import com.example.myhealth.data.api.recipes.MealModel
import com.example.myhealth.databinding.FoodItemBinding
import com.example.myhealth.databinding.ItemBinding

class MealsAdapter (val mealsList: List<MealModel>,
                    val clickHandler: ((mealItem: MealModel) -> Unit))
    : RecyclerView.Adapter<MealsAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(categoryItem: View): RecyclerView.ViewHolder(categoryItem)  {
        val binding = FoodItemBinding.bind(categoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = mealsList[position]
        holder.binding.tvItem.text = currentItem.strMeal
        Glide.with(holder.itemView).load(currentItem.strMealThumb).apply(
            RequestOptions().placeholder(R.drawable.recipe)
            ).into(holder.binding.ivItem)
        holder.binding.root.setOnClickListener{
            clickHandler(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}