package com.example.baseapp_jetpackcompose.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp_jetpackcompose.domain.viewstate.IViewEvent
import com.example.baseapp_jetpackcompose.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: IViewState, Event: IViewEvent> : ViewModel() {
    abstract fun createInitialState(): State
    abstract fun onTriggerEvent(event: Event)

    private val initialState: State by lazy { createInitialState() }

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    val currentState: State get() = uiState.value

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent: SharedFlow<Event> = _uiEvent.asSharedFlow()

    protected fun setState(reduce: State.() -> State){
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEvent(event: Event){
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    protected suspend fun <T> call (
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ){
        callFlow
            .catch {  }
            .collect{
                completionHandler.invoke(it)
            }
    }
}