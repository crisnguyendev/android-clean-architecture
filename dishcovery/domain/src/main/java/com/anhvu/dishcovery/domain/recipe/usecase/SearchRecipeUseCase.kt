package com.anhvu.dishcovery.domain.recipe.usecase

import com.anhvu.dishcovery.domain.recipe.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface SearchRecipeUseCase {
    suspend fun execute(query: String, offset: Int, limit: Int): Flow<List<RecipeModel>>
}