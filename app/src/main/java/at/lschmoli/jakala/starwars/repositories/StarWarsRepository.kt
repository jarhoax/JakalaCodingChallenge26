package at.lschmoli.jakala.starwars.repositories

import at.lschmoli.jakala.starwars.models.Character
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
interface StarWarsRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: Int): Character
}