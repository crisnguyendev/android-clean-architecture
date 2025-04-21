package com.anhvu.dishcovery.feature.recipe.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anhvu.dishcovery.domain.recipe.model.RecipeModel
import com.anhvu.dishcovery.domain.recipe.usecase.SearchRecipeUseCase
import kotlinx.coroutines.flow.first
import java.io.IOException

class SearchRecipePagingSource(private val useCase: SearchRecipeUseCase, private val query: String) :
    PagingSource<Int, RecipeModel>() {
    override fun getRefreshKey(state: PagingState<Int, RecipeModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeModel> {
        val page = params.key ?: 0
        val recipes = useCase.execute(query = query, offset = page * 10, limit = 20).first()
        return if (recipes.isEmpty() && page > 0) {
            LoadResult.Error(IOException("No more result"))
        } else {
            LoadResult.Page(
                data = recipes,
                prevKey = if (page == 0) null else page - 1,
                nextKey = page + 1
            )
        }
    }

}