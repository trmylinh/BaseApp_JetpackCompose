package com.example.baseapp_jetpackcompose.core.domain.viewstate.character

import androidx.paging.PagingData
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto
import com.example.baseapp_jetpackcompose.core.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

data class CharacterViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<CharacterDto>>? = null,
    val data: List<CharacterDto>? = null,
): IViewState {
}