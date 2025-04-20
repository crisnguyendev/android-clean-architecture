package com.anhvu.dishcovery.service.spoonacular

import com.anhvu.dishcovery.service.BuildConfig
import com.anhvu.dishcovery.service.spoonacular.recipe.SearchRecipeDTO
import retrofit2.http.GET
import retrofit2.http.Query


interface SpoonacularAPI {
    @GET("recipes/complexSearch")
    suspend fun searchRecipe(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_API_KEY,
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("number") limit: Int
    ): SearchRecipeDTO
}