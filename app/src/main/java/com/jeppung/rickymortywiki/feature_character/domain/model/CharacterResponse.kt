package com.jeppung.rickymortywiki.feature_character.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

	@field:SerializedName("results")
	val results: List<Character>,

	@field:SerializedName("info")
	val info: Info
)

data class CharacterLocation(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)

data class Character(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("species")
	val species: String,

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("origin")
	val origin: CharacterOrigin,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: CharacterLocation,

	@field:SerializedName("episode")
	val episode: List<String>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("status")
	val status: String
)

data class CharacterOrigin(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)

data class Info(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("pages")
	val pages: Int,

	@field:SerializedName("prev")
	val prev: Any,

	@field:SerializedName("count")
	val count: Int
)
