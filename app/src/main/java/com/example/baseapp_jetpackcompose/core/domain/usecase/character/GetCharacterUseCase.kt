package com.example.baseapp_jetpackcompose.core.domain.usecase.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto
import com.example.baseapp_jetpackcompose.core.base.usecase.BaseUseCase
import com.example.baseapp_jetpackcompose.core.base.usecase.IParams
import com.example.baseapp_jetpackcompose.core.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    internal  val repository: CharacterRepository
): BaseUseCase<GetCharacterUseCase.Params, PagingData<CharacterDto>> {
    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CharacterPagingSource(repository, param.options) }
        ).flow
    }
}