package com.example.baseapp_jetpackcompose.feature.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.baseapp_jetpackcompose.domain.viewstate.IViewEvent
import com.example.baseapp_jetpackcompose.domain.viewstate.splash.SplashViewState
import com.example.baseapp_jetpackcompose.feature.base.BaseViewModel
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