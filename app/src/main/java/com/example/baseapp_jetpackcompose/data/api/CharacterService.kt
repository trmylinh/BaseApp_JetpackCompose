package com.example.baseapp_jetpackcompose.data.api

import com.example.baseapp_jetpackcompose.data.model.CharacterDto
import com.example.baseapp_jetpackcompose.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<CharacterResponse>

    @GET("character/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): Response<CharacterDto>

    @GET("character/{characterId}")
    suspend fun getCharacter(@Url url: String): Response<CharacterDto>

    @GET("character/")
    suspend fun getFilterCharacters(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<CharacterResponse>

}