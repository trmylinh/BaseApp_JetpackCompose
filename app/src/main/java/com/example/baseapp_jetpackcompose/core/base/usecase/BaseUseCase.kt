package com.example.baseapp_jetpackcompose.core.base.usecase

import kotlinx.coroutines.flow.Flow

interface IParams

interface BaseUseCase<T: Any, R: Any> {
    suspend operator fun invoke(params: T): Flow<R>
}