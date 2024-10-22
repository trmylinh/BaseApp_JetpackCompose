package com.example.baseapp_jetpackcompose.core.base

import com.example.baseapp_jetpackcompose.core.utils.DataState
import retrofit2.Response

open class BaseApiResponse {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DataState<T> {
        try{
            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                return if(body != null) DataState.Success(body) else{
                    DataState.Error(response.message())
                }
            }
            return DataState.Error(response.message())
        } catch (e: Exception){
            return DataState.Error(e.message.toString())
        }
    }
}