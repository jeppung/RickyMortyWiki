package com.jeppung.rickymortywiki.feature_character.domain.repository

import androidx.paging.PagingSource
import com.jeppung.rickymortywiki.feature_character.domain.model.Character

interface CharacterRepository {

    fun getCharacters(): PagingSource<Int, Character>

    suspend fun getSingleCharacter(id: Int): Character
}