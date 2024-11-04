package com.example.baseapp_jetpackcompose.di.module

import com.example.baseapp_jetpackcompose.core.utils.Constants
import com.example.baseapp_jetpackcompose.feature.user.domain.api.UserService
import com.example.baseapp_jetpackcompose.feature.user.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideUserRepository(
        userService: UserService
    ): UserRepository = UserRepository(userService)
}