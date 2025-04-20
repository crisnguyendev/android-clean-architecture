package com.anhvu.dishcovery.feature.recipe.repository

import com.anhvu.dishcovery.domain.model.recipe.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun searchRecipe(query: String): Flow<List<RecipeModel>>
}