package com.example.myhealth.di

import com.example.myhealth.data.api.RecipesApi
import com.example.myhealth.data.repository.RecipeRepositoryImpl
import com.example.myhealth.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun getRepository(api: RecipesApi):RecipeRepository = RecipeRepositoryImpl(api)
}