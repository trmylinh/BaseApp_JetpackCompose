package com.example.baseapp_jetpackcompose.feature.character.domain.model

import com.example.baseapp_jetpackcompose.core.data.model.LocationDto
import com.google.gson.Gson
import java.io.Serializable

data class CharacterDto(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: LocationDto?,
    val name: String?,
    val origin: LocationDto?,
    val species: String?,
    val type: String?,
    val url: String?,
    val isFavorite: Boolean = false
): Serializable{
    companion object {
        fun create(jsonString: String): CharacterDto? {
            return try{
                Gson().fromJson(jsonString, CharacterDto::class.java)
            } catch (e: Exception){
                return null
            }
        }
    }
}