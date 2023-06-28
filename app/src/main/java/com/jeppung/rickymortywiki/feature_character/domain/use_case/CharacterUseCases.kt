package com.jeppung.rickymortywiki.feature_character.domain.use_case

data class CharacterUseCases(
    val getCharacters: GetCharacter,
    val getSingleCharacter: GetSingleCharacter
)