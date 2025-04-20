package com.anhvu.dishcovery.feature.recipe.di

import com.anhvu.dishcovery.feature.recipe.repository.RecipeRepository
import com.anhvu.dishcovery.feature.recipe.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository
}