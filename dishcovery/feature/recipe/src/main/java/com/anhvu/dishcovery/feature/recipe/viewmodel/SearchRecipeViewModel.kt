package com.anhvu.dishcovery.feature.recipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anhvu.dishcovery.domain.recipe.model.RecipeModel
import com.anhvu.dishcovery.domain.recipe.usecase.SearchRecipeUseCase
import com.anhvu.dishcovery.feature.recipe.configuration.RecipeConfiguration
import com.anhvu.dishcovery.feature.recipe.data.SearchRecipePagingSource
import com.anhvu.dishcovery.feature.recipe.view.SearchRecipeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchRecipeViewModel @Inject constructor(private val useCase: SearchRecipeUseCase) :
    ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState = MutableStateFlow<SearchRecipeUiState>(SearchRecipeUiState.Loading)
    val uiState: StateFlow<SearchRecipeUiState> = _uiState.asStateFlow()

    private val retryTrigger = MutableStateFlow(0)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val recipes: Flow<PagingData<RecipeModel>> =
        combine(searchQuery, retryTrigger) { query, _ -> query }
            .debounce(300)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                Pager(
                    config = PagingConfig(
                        pageSize = RecipeConfiguration.PAGE_SIZE,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { SearchRecipePagingSource(useCase, query) }
                ).flow
            }
            .onEach { data ->
                _uiState.value = if (data == PagingData.empty<RecipeModel>()) {
                    SearchRecipeUiState.Success(PagingData.empty())
                } else {
                    SearchRecipeUiState.Success(data)
                }
            }
            .catch { throwable ->
                _uiState.value = SearchRecipeUiState.Error(throwable)
            }
            .cachedIn(viewModelScope)

//    init {
//
//        viewModelScope.launch {
//            useCase.invoke("burger", 0, Constants.PAGE_SIZE)
//                .catch { throwable ->
//                    Logger.error(throwable, "Initial fetch failed")
//                    _uiState.value = SearchRecipeUiState.Error(getErrorMessage(throwable))
//                }
//                .collectLatest { recipes ->
//                    recipes.forEach { recipe ->
//                        Logger.log("Initial Recipe: ${recipe.title}, ID: ${recipe.id}", "SearchRecipeViewModel")
//                    }
//                }
//        }
//    }

    fun updateSearchQuery(query: String) {
        _uiState.value =
            if (query.isEmpty()) SearchRecipeUiState.Success(PagingData.empty()) else SearchRecipeUiState.Loading
        _searchQuery.value = query
    }

    fun retry() {
        retryTrigger.value += 1
    }

//    private fun getErrorMessage(throwable: Throwable): String {
//        return when (throwable) {
//            is IOException -> R.string.core_error_network.toString()
//            else -> R.string.core_error_unknown.toString()
//        }
//    }
}