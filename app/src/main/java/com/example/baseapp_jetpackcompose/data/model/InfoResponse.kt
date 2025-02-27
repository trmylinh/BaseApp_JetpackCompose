package com.example.baseapp_jetpackcompose.data.model

import java.io.Serializable

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
): Serializable{
}