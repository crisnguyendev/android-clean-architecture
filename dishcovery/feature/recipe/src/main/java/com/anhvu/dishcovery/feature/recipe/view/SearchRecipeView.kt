//package com.anhvu.dishcovery.feature.recipe.view
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.paging.LoadState
//import androidx.paging.PagingData
//import androidx.paging.compose.LazyPagingItems
//import androidx.paging.compose.collectAsLazyPagingItems
//import com.anhvu.dishcovery.core.ui.ErrorMessage
//import com.anhvu.dishcovery.core.ui.LoadMoreIndicator
//import com.anhvu.dishcovery.core.ui.RecipeItem
//import com.google.accompanist.swiperefresh.SwipeRefresh
//import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
//import kotlinx.coroutines.flow.Flow
//
//@Composable
//fun RecipeSearchScreen(viewModel: SearchRecipeViewModel = hiltViewModel()) {
//    val searchQuery = viewModel.searchQuery.collectAsState().value
//    val uiState = viewModel.uiState.collectAsState().value
//    val recipes = viewModel.recipes
//
//    SearchablePagingList(
//        query = searchQuery,
//        onQueryChange = { viewModel.updateSearchQuery(it) },
//        uiState = uiState,
//        pagingData = recipes,
//        onRefresh = { viewModel.retry() },
//        onRetry = { viewModel.retry() },
//        itemContent = { recipe ->
//            RecipeItem(
//                title = recipe.title,
//                image = recipe.image
//            )
//        },
//        modifier = Modifier.fillMaxSize()
//    )
//}
//
//@Composable
//fun <T : Any> SearchablePagingList(
//    query: String,
//    onQueryChange: (String) -> Unit,
//    uiState: SearchRecipeUiState,
//    pagingData: Flow<PagingData<T>>,
//    onRefresh: () -> Unit,
//    onRetry: () -> Unit,
//    itemContent: @Composable (T) -> Unit,
//    modifier: Modifier = Modifier,
//    emptyMessage: String = stringResource(R.string.empty_message),
//    contentPadding: PaddingValues = PaddingValues(16.dp)
//) {
//    val pagingItems = pagingData.collectAsLazyPagingItems()
//    val swipeRefreshState = rememberSwipeRefreshState(
//        isRefreshing = pagingItems.loadState.refresh is LoadState.Loading
//    )
//
//    Column(
//        modifier = modifier.padding(contentPadding)
//    ) {
//        SearchBar(
//            query = query,
//            onQueryChange = onQueryChange,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        )
//
//        when (uiState) {
//            is SearchRecipeUiState.Loading -> {
//                LoadingView(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .wrapContentSize()
//                )
//            }
//
//            is SearchRecipeUiState.Error -> {
//                ErrorView(
//                    message = uiState.message,
//                    onRetry = onRetry,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//
//            is SearchRecipeUiState.Success -> {
//                SwipeRefresh(
//                    state = swipeRefreshState,
//                    onRefresh = {
//                        onRefresh()
//                        pagingItems.refresh()
//                    }
//                ) {
//                    PagingList(
//                        pagingItems = pagingItems,
//                        emptyMessage = emptyMessage,
//                        itemContent = itemContent,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SearchBar(
//    query: String,
//    onQueryChange: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    TextField(
//        value = query,
//        onValueChange = onQueryChange,
//        modifier = modifier,
//        placeholder = { Text(stringResource(R.string.search_placeholder)) },
//        singleLine = true,
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = MaterialTheme.colors.surface
//        )
//    )
//}
//
//@Composable
//fun LoadingView(
//    modifier: Modifier = Modifier
//) {
//    CircularProgressIndicator(
//        modifier = modifier
//    )
//}
//
//@Composable
//fun ErrorView(
//    message: String,
//    onRetry: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        ErrorMessage(message)
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(
//            onClick = onRetry,
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = MaterialTheme.colors.primary
//            )
//        ) {
//            Text("Retry")
//        }
//    }
//}
//
//@Composable
//fun <T : Any> PagingList(
//    pagingItems: LazyPagingItems<T>,
//    emptyMessage: String,
//    itemContent: @Composable (T) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    LazyColumn(
//        modifier = modifier
//    ) {
//        if (pagingItems.itemCount == 0 && pagingItems.loadState.refresh !is LoadState.Loading) {
//            item {
//                Text(
//                    text = emptyMessage,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.body1
//                )
//            }
//        }
//
//        items(pagingItems) { item ->
//            item?.let { itemContent(it) }
//        }
//
//        item {
//            if (pagingItems.loadState.append is LoadState.Loading) {
//                LoadMoreIndicator()
//            }
//        }
//    }
//}
//
//private val EmptyMessage = R.string.empty_message