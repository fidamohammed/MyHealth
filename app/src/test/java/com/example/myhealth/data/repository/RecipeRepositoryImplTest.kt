package com.example.myhealth.data.repository

import com.example.myhealth.data.api.RecipesApi
import com.example.myhealth.data.api.recipeCategories.CategoryModel
import com.example.myhealth.data.api.recipeCategories.RecipeCategoriesModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RecipeRepositoryImplTest{
    lateinit var repositoryImpl: RecipeRepositoryImpl

    @Mock
    lateinit var mealsApi: RecipesApi

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repositoryImpl = RecipeRepositoryImpl(mealsApi)
    }

    @Test
    fun `getCategories with data`() = runBlocking{
        whenever(mealsApi.getCategories()).thenReturn(RecipeCategoriesModel( listOf(CategoryModel("123","TestCategory","TestDescription","TestImage"))) )
        val result = repositoryImpl.getCategories()
        assertEquals(
            RecipeCategoriesModel( listOf(CategoryModel("123","TestCategory","TestDescription","TestImage"))),
            result
        )
    }



}