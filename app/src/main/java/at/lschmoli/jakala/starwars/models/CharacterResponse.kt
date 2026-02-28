package at.lschmoli.jakala.starwars.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(val results: List<Character>)

@Serializable
data class Character(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String
)