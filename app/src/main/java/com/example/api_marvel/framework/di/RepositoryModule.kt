package com.example.api_marvel.framework.di

import com.example.api_marvel.framework.CharactersRepositoryImpl
import com.example.api_marvel.framework.di.network.response.DataWrapperResponse
import com.example.api_marvel.remote.RetrofitCharactersDataSource
import com.example.core.repository.CharactersRemoteDataSource
import com.example.core.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repositoryImpl: CharactersRepositoryImpl) : CharactersRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitCharactersDataSource
    ) : CharactersRemoteDataSource<DataWrapperResponse>


}