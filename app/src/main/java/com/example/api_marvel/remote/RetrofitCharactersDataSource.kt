package com.example.api_marvel.remote

import com.example.api_marvel.framework.di.network.MarvelApi
import com.example.api_marvel.framework.di.network.response.DataWrapperResponse
import com.example.core.repository.CharactersRemoteDataSource
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
): CharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
       return marvelApi.getCharacters(queries)
    }
}