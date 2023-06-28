package com.jeppung.rickymortywiki.feature_character.domain.use_case

import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.feature_character.domain.repository.CharacterRepository

class GetSingleCharacter(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(id: Int): Character {
        return repository.getSingleCharacter(id)
    }
}