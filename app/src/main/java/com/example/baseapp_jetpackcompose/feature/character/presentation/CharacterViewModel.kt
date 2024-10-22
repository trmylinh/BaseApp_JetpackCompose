package com.example.baseapp_jetpackcompose.feature.character.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.baseapp_jetpackcompose.core.base.viewmodel.BaseViewModel
import com.example.baseapp_jetpackcompose.core.domain.usecase.character.GetCharacterUseCase
import com.example.baseapp_jetpackcompose.core.domain.viewstate.IViewEvent
import com.example.baseapp_jetpackcompose.core.domain.viewstate.character.CharacterViewState
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CharacterViewEvent : IViewEvent {
    class UpdateFavorite(val dto: CharacterDto) : CharacterViewEvent()
}

class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
): BaseViewModel<CharacterViewState, CharacterViewEvent>(){
    private val config = PagingConfig(pageSize = 20)

    init {
        getAllCharacters()
    }

    private fun getAllCharacters(){
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = GetCharacterUseCase.Params(config, hashMapOf())
            val pagedFlow = getCharacterUseCase(params).cachedIn(viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    override fun createInitialState(): CharacterViewState = CharacterViewState()

    override fun onTriggerEvent(event: CharacterViewEvent) {
        viewModelScope.launch {
            when(event){
                is CharacterViewEvent.UpdateFavorite -> {

                }
            }
        }
    }
}