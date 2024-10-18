package com.example.baseapp_jetpackcompose.data.model

import java.io.Serializable

data class LocationDto(
    val locationId: Int?,
    val name: String?,
    val url: String?,
): Serializable {
}