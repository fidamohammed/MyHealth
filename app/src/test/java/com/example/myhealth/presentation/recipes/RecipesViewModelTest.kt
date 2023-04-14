package com.example.myhealth.presentation.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.myhealth.data.api.recipeCategories.CategoryModel
import com.example.myhealth.data.api.recipeCategories.RecipeCategoriesModel
import com.example.myhealth.data.repository.RecipeRepositoryImpl
import com.example.myhealth.util.ResultStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

class RecipesViewModelTest{
    lateinit var recipesViewModel: RecipesViewModel

    @Mock
    lateinit var repository : RecipeRepositoryImpl

    val dispatcher = TestCoroutineDispatcher()
    @get:Rule
    var instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        recipesViewModel = RecipesViewModel(repository)
    }

    @Test
    fun `getCategories with data`() = runBlocking{
        whenever(repository.getCategories()).thenReturn(RecipeCategoriesModel(listOf(CategoryModel("123","TestCategory","TestDescription","TestImage"))))
        recipesViewModel.getCategories()
        recipesViewModel.categories.asLiveData().observeForever{

            assertEquals(
                listOf(CategoryModel("123","TestCategory","TestDescription","TestImage")),
                (it as ResultStates.Success<*>).data
            )
        }
    }

    @Test
    fun `getCategories with empty error`() = runBlocking{
        whenever(repository.getCategories()).thenReturn(RecipeCategoriesModel(listOf()))
        recipesViewModel.getCategories()
        recipesViewModel.categories.asLiveData().observeForever{

            assertEquals(
                "Empty Response",
                (it as ResultStates.Error).error
            )
        }
    }

    @Test
    fun `getCategories with error`() = runBlocking{
        whenever(repository.getCategories()).thenThrow(HttpException(Response.error<ResponseBody>(404,ResponseBody.create("plain/text".toMediaType(),""))))
        recipesViewModel.getCategories()
        recipesViewModel.categories.asLiveData().observeForever{

            assertEquals(
                "Something went wrong: HTTP 404 Response.error()",
                (it as ResultStates.Error).error
            )
        }
    }

}