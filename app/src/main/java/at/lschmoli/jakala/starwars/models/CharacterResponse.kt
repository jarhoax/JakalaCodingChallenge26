package at.lschmoli.jakala.starwars.models

import kotlinx.serialization.Serializable

@Serializable
@kotlinx.serialization.InternalSerializationApi
data class CharacterResponse(val results: List<Character>)

@Serializable
@kotlinx.serialization.InternalSerializationApi
data class Character(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String
)