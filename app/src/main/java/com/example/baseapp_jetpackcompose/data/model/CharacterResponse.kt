package com.example.baseapp_jetpackcompose.data.model

import java.io.Serializable

data class CharacterResponse(
    val info: InfoResponse,
    val results: List<CharacterDto>
): Serializable{
}