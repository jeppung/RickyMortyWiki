package com.jeppung.rickymortywiki.feature_character.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.jeppung.rickymortywiki.feature_character.data.data_source.CharacterPagingSource
import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.feature_character.domain.model.CharacterResponse
import com.jeppung.rickymortywiki.feature_character.domain.repository.CharacterRepository
import com.jeppung.rickymortywiki.network.ApiService

class CharacterRepositoryImpl(
    private val apiService: ApiService
): CharacterRepository {

    override fun getCharacters(): PagingSource<Int, Character> {
        return CharacterPagingSource(apiService)
    }

    override suspend fun getSingleCharacter(id: Int): Character {
        return apiService.getSingleCharacter(id)
    }
}