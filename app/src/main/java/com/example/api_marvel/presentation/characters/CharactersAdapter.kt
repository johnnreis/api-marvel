package com.example.api_marvel.presentation.characters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.domain.model.Character

class CharactersAdapter : ListAdapter<Character, CharactersViewHolder>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bing(getItem(position))
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}