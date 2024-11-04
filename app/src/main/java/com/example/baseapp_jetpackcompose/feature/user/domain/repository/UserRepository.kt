package com.example.baseapp_jetpackcompose.feature.user.domain.repository

import com.example.baseapp_jetpackcompose.core.utils.DataState
import com.example.baseapp_jetpackcompose.feature.user.data.model.User
import com.example.baseapp_jetpackcompose.feature.user.domain.api.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
){
    fun getUser(characterId: Int): Flow<Response<User>> {
        return flow {
            emit(userService.getUser(characterId))
        }.flowOn(Dispatchers.IO)
    }
}