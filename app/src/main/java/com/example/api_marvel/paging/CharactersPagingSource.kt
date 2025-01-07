package com.example.api_marvel.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api_marvel.framework.di.network.response.DataWrapperResponse
import com.example.api_marvel.framework.di.network.response.toCharacterModel
import com.example.core.domain.model.Character
import com.example.core.repository.CharactersRemoteDataSource

class CharactersPagingSource(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, Character>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
      return  try {
          @Suppress("MagicNumber")
            val offset = params.key ?: 0

            val queries = hashMapOf("offset" to offset.toString())

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val response = remoteDataSource.fetchCharacters(queries)

            val responseOffSet = response.data.offset
            val totalCharacters = response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if (responseOffSet < totalCharacters) { responseOffSet + LIMIT } else null
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override  fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }

}