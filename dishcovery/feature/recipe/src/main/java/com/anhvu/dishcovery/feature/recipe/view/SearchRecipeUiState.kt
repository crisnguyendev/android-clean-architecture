package com.anhvu.dishcovery.feature.recipe.view

import androidx.paging.PagingData
import com.anhvu.dishcovery.domain.recipe.model.RecipeModel

sealed class SearchRecipeUiState {
    object Loading : SearchRecipeUiState()
    data class Success(val data: PagingData<RecipeModel>) : SearchRecipeUiState()
    data class Error(val throwable: Throwable) : SearchRecipeUiState()
}