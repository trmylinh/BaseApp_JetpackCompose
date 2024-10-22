package com.example.baseapp_jetpackcompose.feature.character.domain.model

import com.example.baseapp_jetpackcompose.core.data.model.InfoResponse
import java.io.Serializable

data class CharacterResponse(
    val info: InfoResponse,
    val results: List<CharacterDto>
): Serializable{
}