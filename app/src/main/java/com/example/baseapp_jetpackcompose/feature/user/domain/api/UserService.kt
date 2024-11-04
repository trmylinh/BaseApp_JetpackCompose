package com.example.baseapp_jetpackcompose.feature.user.domain.api

import com.example.baseapp_jetpackcompose.feature.user.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("character/{characterId}")
    suspend fun getUser(@Path("characterId") characterId: Int): Response<User>
}