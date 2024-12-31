package com.example.api_marvel.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_marvel.R
import com.example.api_marvel.databinding.ItemCharactersBinding
import com.example.core.domain.model.Character

class CharactersViewHolder(
    itemCharacterBinding : ItemCharactersBinding
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    private val textName = itemCharacterBinding.textName
    private val imageCharacter = itemCharacterBinding.imageCharacter

    fun bing(character: Character) {
        textName.text = character.name
        Glide.with(itemView)
            .load(character.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageCharacter)
    }

    companion object {
        fun create(parent: ViewGroup) : CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharactersBinding.inflate(inflater, parent, false)

            return CharactersViewHolder(itemBinding)
        }
    }

}