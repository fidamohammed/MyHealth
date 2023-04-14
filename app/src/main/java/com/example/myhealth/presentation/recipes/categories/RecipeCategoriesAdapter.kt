package com.example.myhealth.presentation.recipes.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealth.R
import com.example.myhealth.data.api.recipeCategories.CategoryModel
import com.example.myhealth.databinding.ItemBinding

class RecipeCategoriesAdapter(val categoryList: List<CategoryModel>,
                              val clickHandler: ((categoryItem: CategoryModel) -> Unit))
    : RecyclerView.Adapter<RecipeCategoriesAdapter.ItemViewHolder>() {
    class ItemViewHolder(categoryItem: View): RecyclerView.ViewHolder(categoryItem)  {
        val binding = ItemBinding.bind(categoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.binding.tvItem.text = currentItem.strCategory
        holder.binding.root.setOnClickListener{
            clickHandler(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}