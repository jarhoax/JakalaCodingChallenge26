package at.lschmoli.jakala.starwars.repositories

import at.lschmoli.jakala.starwars.models.Character

interface StarWarsRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: Int): Character
}