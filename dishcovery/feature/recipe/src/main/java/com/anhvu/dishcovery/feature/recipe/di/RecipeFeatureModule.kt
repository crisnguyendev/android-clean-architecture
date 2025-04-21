package com.anhvu.dishcovery.feature.recipe.di

import com.anhvu.dishcovery.domain.recipe.repository.RecipeRepository
import com.anhvu.dishcovery.domain.recipe.usecase.SearchRecipeUseCase
import com.anhvu.dishcovery.feature.recipe.data.RecipeRepositoryImpl
import com.anhvu.dishcovery.feature.recipe.usecase.SearchRecipeUseCaseImpl
import com.anhvu.dishcovery.service.spoonacular.SpoonacularAPI
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RecipeFeatureModule {
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    @Singleton
    abstract fun provideSearchRecipeUseCase(impl: SearchRecipeUseCaseImpl): SearchRecipeUseCase
}