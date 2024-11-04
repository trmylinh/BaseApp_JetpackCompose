package com.example.baseapp_jetpackcompose.feature.user.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp_jetpackcompose.feature.user.data.model.User
import com.example.baseapp_jetpackcompose.feature.user.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _responseUser: MutableStateFlow<User?> = MutableStateFlow(null)
    val responseUser: StateFlow<User?> = _responseUser

    init {
        getUser(1)
    }

    private fun getUser(characterId: Int) = viewModelScope.launch {
        userRepository.getUser(characterId).collect{ values ->
            _responseUser.value = values.body()
            Log.d("UserViewModel", "getUser: ${values.body()}")
        }
    }
}