package at.lschmoli.jakala.starwars.fakes

import at.lschmoli.jakala.starwars.models.Character
import at.lschmoli.jakala.starwars.repositories.StarWarsRepository

class FailingStarWarsRepository : StarWarsRepository {

    override suspend fun getCharacters(): List<Character> = throw RuntimeException("Network error")

    override suspend fun getCharacter(id: Int): Character = throw RuntimeException("Network error")
}