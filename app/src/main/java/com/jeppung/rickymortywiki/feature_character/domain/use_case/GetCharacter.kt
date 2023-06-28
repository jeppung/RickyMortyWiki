package com.jeppung.rickymortywiki.feature_character.domain.use_case

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.feature_character.domain.model.CharacterResponse
import com.jeppung.rickymortywiki.feature_character.domain.repository.CharacterRepository

class GetCharacter(
    private val repository: CharacterRepository
) {

    operator fun invoke(): PagingSource<Int, Character> {
        return repository.getCharacters()
    }
}