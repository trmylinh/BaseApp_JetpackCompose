package com.example.baseapp_jetpackcompose.core.domain.usecase.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto
import com.example.baseapp_jetpackcompose.core.domain.repository.CharacterRepository
import okio.IOException

class CharacterPagingSource(
    internal val repository: CharacterRepository,
    private val options: Map<String, String>
): PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPositon ->
            val anchorPage = state.closestPageToPosition(anchorPositon)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
       val page = params.key ?: 1
        return try {
            val response = repository.getAllCharacters(page, options)

            val characters = if(response.isSuccessful){
                response.body()?.results.orEmpty()
            } else {
                emptyList()
            }

//            if(characters.isNotEmpty()){
//                characters.map {
//
//                }
//            }

            LoadResult.Page(
                data = characters,
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(characters.isEmpty()) null else page+1
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }
    }
}