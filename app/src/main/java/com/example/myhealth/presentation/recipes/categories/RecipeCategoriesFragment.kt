package com.example.myhealth.presentation.recipes.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealth.databinding.FragmentRecipeCategoriesBinding
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.myhealth.R
import com.example.myhealth.data.api.recipeCategories.CategoryModel
import com.example.myhealth.presentation.recipes.RecipesViewModel
import com.example.myhealth.util.ResultStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeCategoriesFragment : Fragment() {

    private lateinit var binding: FragmentRecipeCategoriesBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeCategoriesBinding.inflate(layoutInflater)

        recyclerView = binding.rvAllCategories
        recyclerView.layoutManager = LinearLayoutManager(context)

        val recipeViewModel: RecipesViewModel by activityViewModels()

        recipeViewModel.getCategories()
        recipeViewModel.categories.asLiveData().observe(viewLifecycleOwner){ state->
            when(state){
                is ResultStates.Success<*> -> {
                    binding.progressBar.isVisible = false
                    recyclerView.adapter = RecipeCategoriesAdapter(state.data as List<CategoryModel>){
                        recipeViewModel.currentCategory = it.strCategory
                        findNavController().navigate(R.id.action_recipeCategoriesFragment_to_recipeItemsFragment)
                    }
                }
                is ResultStates.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(context,"${state.error} ",Toast.LENGTH_SHORT).show()
                }
                is ResultStates.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }

        return binding.root

    }


}