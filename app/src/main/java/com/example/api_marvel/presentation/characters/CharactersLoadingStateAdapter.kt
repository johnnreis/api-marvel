package com.example.api_marvel.presentation.characters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharactersLoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadMoreStateViewHolder>(){


    override fun onBindViewHolder(
        holder: CharactersLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadMoreStateViewHolder {
        return CharactersLoadMoreStateViewHolder.create(parent, retry)
    }


}