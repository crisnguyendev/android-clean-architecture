package com.anhvu.dishcovery.feature.recipe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.anhvu.dishcovery.uikit.ErrorView
import com.anhvu.dishcovery.uikit.LoadingView
import com.anhvu.dishcovery.uikit.PagingList
import com.anhvu.dishcovery.uikit.R
import com.anhvu.dishcovery.uikit.SearchBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.Flow


@Composable
fun <T : Any> SearchablePagingList(
    query: String,
    onQueryChange: (String) -> Unit,
    uiState: SearchRecipeUiState,
    pagingData: Flow<PagingData<T>>,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
    itemContent: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
    emptyMessage: String = stringResource(R.string.core_empty_message),
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.core_standard_padding))
) {
    val pagingItems = pagingData.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = pagingItems.loadState.refresh is LoadState.Loading
    )

    Column(
        modifier = modifier.padding(contentPadding)
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.core_small_spacing))
        )

        when (uiState) {
            is SearchRecipeUiState.Loading -> {
                LoadingView(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
            is SearchRecipeUiState.Error -> {
                ErrorView(
                    message = uiState.throwable.message.toString(),
                    onRetry = onRetry,
                    modifier = Modifier.fillMaxSize()
                )
            }
            is SearchRecipeUiState.Success -> {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        onRefresh()
                        pagingItems.refresh()
                    }
                ) {
                    PagingList(
                        pagingItems = pagingItems,
                        emptyMessage = emptyMessage,
                        itemContent = itemContent,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}