package com.example.baseapp_jetpackcompose.feature.character.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.baseapp_jetpackcompose.core.utils.Utility.rememberFlowWithLifecycle
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto
import com.example.baseapp_jetpackcompose.feature.component.CharacterCard
import com.example.baseapp_jetpackcompose.feature.component.CustomScaffold
import com.example.baseapp_jetpackcompose.feature.component.CustomTopBar
import kotlinx.coroutines.flow.Flow

@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel,
    navigateToDetail: (CharacterDto?) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value

    CustomScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopBar(text = "Character", elevation = 10.dp)
        },
        content = {
            Content(
                onTriggerEvent = {
                    viewModel.onTriggerEvent(it)
                },
                isLoading = viewState.isLoading,
                pagedData = viewState.pagedData,
                clickDetail = {
                    navigateToDetail.invoke(it)
                },
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun Content(
    isLoading: Boolean = false,
    pagedData: Flow<PagingData<CharacterDto>>? = null,
    onTriggerEvent: (CharacterViewEvent) -> Unit,
    clickDetail: (CharacterDto?) -> Unit,
) {
    var pagingItems: LazyPagingItems<CharacterDto>? = null
    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isLoading) {
                items(10) {

                }
            } else if (pagedData != null && pagingItems != null) {
                items(pagingItems!!.itemCount) { index ->
                    CharacterCard(
                        character = pagingItems!![index],
                        detailClick = {
                            clickDetail.invoke(pagingItems!![index])
                        },
                        onTriggerEvent = {},
                    )
                }
            }

        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun DetailContentItemViewPreview() {
    CharacterScreen(viewModel = hiltViewModel(), navigateToDetail = {})
}