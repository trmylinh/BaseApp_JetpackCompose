package com.example.baseapp_jetpackcompose.domain.repository

import com.example.baseapp_jetpackcompose.data.model.CharacterDto
import com.example.baseapp_jetpackcompose.data.model.CharacterResponse
import com.example.baseapp_jetpackcompose.data.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    fun getCharacter(characterId: Int): Flow<DataState<CharacterDto>>
    fun getCharacter(url: String): Flow<DataState<CharacterDto>>
    suspend fun getFilterCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
}