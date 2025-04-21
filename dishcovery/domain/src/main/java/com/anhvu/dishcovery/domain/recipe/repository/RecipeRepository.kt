package com.anhvu.dishcovery.domain.recipe.repository

import com.anhvu.dishcovery.domain.recipe.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun searchRecipe(query: String, offset: Int, limit: Int): Flow<List<RecipeModel>>
}