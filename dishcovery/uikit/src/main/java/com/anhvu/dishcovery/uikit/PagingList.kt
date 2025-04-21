package com.anhvu.dishcovery.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import java.util.UUID

@Composable
fun <T : Any> PagingList(
    pagingItems: LazyPagingItems<T>,
    emptyMessage: String,
    itemContent: @Composable (T) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        if (pagingItems.itemCount == 0 && pagingItems.loadState.refresh !is LoadState.Loading) {
            item {
                Text(
                    text = emptyMessage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.core_standard_padding)),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

//        items(
//            items = pagingItems,
//            key = { item -> item?.hashCode() ?: UUID.randomUUID().toString() }, // Adjust key if T has a unique ID
//            contentType = { "item" }
//        ) { item ->
//            item?.let { itemContent(it) }
//        }

        item {
            if (pagingItems.loadState.append is LoadState.Loading) {
                LoadMoreIndicator()
            }
        }
    }
}