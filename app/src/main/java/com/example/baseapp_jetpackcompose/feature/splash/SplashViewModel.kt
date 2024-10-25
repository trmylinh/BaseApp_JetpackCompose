package com.example.baseapp_jetpackcompose.feature.splash

import androidx.lifecycle.viewModelScope
import com.example.baseapp_jetpackcompose.core.domain.viewstate.IViewEvent
import com.example.baseapp_jetpackcompose.core.domain.viewstate.splash.SplashViewState
import com.example.baseapp_jetpackcompose.core.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(): BaseViewModel<SplashViewState, SplashViewEvent>(){
    init {
        viewModelScope.launch {
            delay(2000)
            setEvent(SplashViewEvent.DirectToDashBoard)
        }
    }
    override fun createInitialState(): SplashViewState = SplashViewState()

    override fun onTriggerEvent(event: SplashViewEvent) {}
}

sealed class SplashViewEvent : IViewEvent {
    object DirectToDashBoard : SplashViewEvent()
}