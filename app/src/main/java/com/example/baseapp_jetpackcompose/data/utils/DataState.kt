package com.example.baseapp_jetpackcompose.data.utils

sealed class DataState<T> {
    class Success<T>(val data: T) : DataState<T>()
    class Loading<T> : DataState<T>()
    class Error<T>(val message: String) : DataState<T>()
}