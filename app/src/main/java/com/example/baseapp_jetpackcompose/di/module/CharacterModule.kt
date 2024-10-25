package com.example.baseapp_jetpackcompose.di.module

import androidx.compose.runtime.Stable
import com.example.baseapp_jetpackcompose.core.domain.repository.CharacterRepository
import com.example.baseapp_jetpackcompose.core.domain.usecase.character.GetCharacterUseCase
import com.example.baseapp_jetpackcompose.feature.character.domain.api.CharacterService
import com.example.baseapp_jetpackcompose.feature.character.domain.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Stable
@Module
@InstallIn(ViewModelComponent::class)
class CharacterModule {
    @Provides
    fun provideCharacterRepository(
        characterService: CharacterService
    ): CharacterRepository = CharacterRepositoryImpl(characterService)

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =  retrofit.create(CharacterService::class.java)

    @Provides
    fun provideGetCharacterUseCase(
        repository: CharacterRepository
    ): GetCharacterUseCase = GetCharacterUseCase(repository)

}