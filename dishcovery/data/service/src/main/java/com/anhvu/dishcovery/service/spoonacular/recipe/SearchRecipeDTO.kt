package com.anhvu.dishcovery.service.spoonacular.recipe

data class SearchRecipeDTO(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val results: List<RecipeDTO>
)