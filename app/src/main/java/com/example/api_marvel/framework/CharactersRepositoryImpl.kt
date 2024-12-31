package com.example.api_marvel.framework

import androidx.paging.PagingSource
import com.example.api_marvel.framework.di.network.response.DataWrapperResponse
import com.example.core.domain.model.Character
import com.example.core.repository.CharactersRemoteDataSource
import com.example.core.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        TODO("Implementar o CharactersPaging")
    }
}