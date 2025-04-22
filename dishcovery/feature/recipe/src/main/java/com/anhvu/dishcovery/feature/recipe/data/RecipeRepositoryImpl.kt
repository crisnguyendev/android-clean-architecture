package com.anhvu.dishcovery.feature.recipe.data

import android.util.Log
import coil.util.Logger
import com.anhvu.dishcovery.domain.recipe.model.RecipeModel
import com.anhvu.dishcovery.domain.recipe.repository.RecipeRepository
import com.anhvu.dishcovery.service.spoonacular.SpoonacularAPI
import com.anhvu.dishcovery.service.spoonacular.recipe.RecipeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//class RecipeRepositoryImpl @Inject constructor(private val api: SpoonacularAPI) : RecipeRepository {
//    override suspend fun searchRecipe(
//        query: String,
//        offset: Int,
//        limit: Int
//    ): Flow<List<RecipeModel>> = flow {
//        val result = api.searchRecipe(query = query, offset = offset, limit = limit)
//        val recipeModels = result.results.map { dto ->
//            RecipeModel(
//                id = dto.id,
//                title = dto.title,
//                image = dto.image,
//                imageType = dto.imageType,
//            )
//        }
//        emit(recipeModels)
//    }.flowOn(Dispatchers.IO)
//}

class RecipeRepositoryImpl @Inject constructor(private val api: SpoonacularAPI) : RecipeRepository {
    override suspend fun searchRecipe(query: String, offset: Int, limit: Int): Flow<List<RecipeModel>> = flow {
        val result = api.searchRecipe(query = query, offset = offset, limit = limit)
        val recipeModels = try {
            result.results.filterIsInstance<RecipeDTO>().map { dto ->
                RecipeModel(
                    id = dto.id,
                    title = dto.title,
                    image = dto.image,
                    imageType = dto.imageType,
                )
            } ?: emptyList()
        } catch (e: Exception) {

            emptyList()
        }

        emit(recipeModels)
    }.flowOn(Dispatchers.IO)
}