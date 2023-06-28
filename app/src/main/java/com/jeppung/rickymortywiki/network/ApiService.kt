package com.jeppung.rickymortywiki.network

import com.jeppung.rickymortywiki.feature_character.domain.model.Character
import com.jeppung.rickymortywiki.feature_character.domain.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): Character
}