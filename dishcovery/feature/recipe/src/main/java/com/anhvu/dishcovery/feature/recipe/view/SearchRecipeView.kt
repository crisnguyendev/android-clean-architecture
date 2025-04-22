import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.anhvu.dishcovery.feature.recipe.view.RecipeItem
import com.anhvu.dishcovery.feature.recipe.view.SearchablePagingList
import com.anhvu.dishcovery.feature.recipe.viewmodel.SearchRecipeViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchRecipeScreen (viewModel: SearchRecipeViewModel = hiltViewModel()) {
    val searchQuery = viewModel.searchQuery.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value
    val recipes = viewModel.recipes

    SearchablePagingList(
        query = searchQuery,
        onQueryChange = { viewModel.updateSearchQuery(it) },
        uiState = uiState,
        pagingData = recipes,
        onRefresh = { viewModel.retry() },
        onRetry = { viewModel.retry() },
        itemContent = { recipe ->
            RecipeItem(
                title = recipe.title,
                image = recipe.image
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}

