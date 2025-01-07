package com.example.api_marvel.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.api_marvel.databinding.FragmentProgressLoadingBinding

class CharactersLoadMoreStateViewHolder (
    itemBinding: FragmentProgressLoadingBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = FragmentProgressLoadingBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoading
    private val textTryAgainMessage = binding.textTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }


    fun bind(loadState: LoadState) {
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit) : CharactersLoadMoreStateViewHolder {
            val itemBinding = FragmentProgressLoadingBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return CharactersLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}