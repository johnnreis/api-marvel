package com.example.api_marvel.framework.di.network

import com.example.api_marvel.framework.di.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters (
        @QueryMap
        queries: Map<String, String>
    ) : DataWrapperResponse

}