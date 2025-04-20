package com.anhvu.dishcovery.feature.recipe.repository

import com.anhvu.dishcovery.domain.model.recipe.RecipeModel
import com.anhvu.dishcovery.service.spoonacular.SpoonacularAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val api: SpoonacularAPI) : RecipeRepository {
    override suspend fun searchRecipe(query: String): Flow<List<RecipeModel>> = flow {
        val result = api.searchRecipe(query = "Burger", offset = 0, limit = 10)
        val recipeModels = result.results.map { dto ->
            RecipeModel(
                id = dto.id,
                title = dto.title,
                image = dto.image,
                imageType = dto.imageType,
            )
        }
        emit(recipeModels)
    }.flowOn(Dispatchers.IO)
}