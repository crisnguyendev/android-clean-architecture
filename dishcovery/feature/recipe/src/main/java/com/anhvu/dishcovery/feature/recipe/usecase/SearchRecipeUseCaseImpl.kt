package com.anhvu.dishcovery.feature.recipe.usecase

import com.anhvu.dishcovery.domain.recipe.model.RecipeModel
import com.anhvu.dishcovery.domain.recipe.repository.RecipeRepository
import com.anhvu.dishcovery.domain.recipe.usecase.SearchRecipeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipeUseCaseImpl @Inject constructor(private val repository: RecipeRepository) :
    SearchRecipeUseCase {
    override suspend fun execute(query: String, offset: Int, limit: Int): Flow<List<RecipeModel>> {
        return repository.searchRecipe(query = query, offset = offset, limit = limit)
    }
}