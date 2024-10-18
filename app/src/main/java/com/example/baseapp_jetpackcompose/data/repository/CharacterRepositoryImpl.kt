package com.example.baseapp_jetpackcompose.data.repository

import com.example.baseapp_jetpackcompose.data.api.CharacterService
import com.example.baseapp_jetpackcompose.data.base.BaseApiResponse
import com.example.baseapp_jetpackcompose.data.model.CharacterDto
import com.example.baseapp_jetpackcompose.data.model.CharacterResponse
import com.example.baseapp_jetpackcompose.data.utils.DataState
import com.example.baseapp_jetpackcompose.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
): BaseApiResponse(), CharacterRepository {
    override suspend fun getAllCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> = characterService.getAllCharacters(page, options)


    override fun getCharacter(characterId: Int): Flow<DataState<CharacterDto>> = flow {
        emit(getResult {
            characterService.getCharacter(characterId)
        })
    }.flowOn(Dispatchers.IO)

    override fun getCharacter(url: String): Flow<DataState<CharacterDto>> = flow {
        emit(getResult {
            characterService.getCharacter(url)
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun getFilterCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> = characterService.getFilterCharacters(page, options)

}