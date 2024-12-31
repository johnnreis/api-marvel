package com.example.api_marvel.framework.di.network.response

data class CharacterResponse(
    val id: String,
    val name: String,
    val thumbnail: ThumbnailResponse
)
