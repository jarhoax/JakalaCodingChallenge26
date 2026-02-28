package at.lschmoli.jakala.starwars.fakes

import at.lschmoli.jakala.starwars.models.Character
import at.lschmoli.jakala.starwars.repositories.StarWarsRepository

class FakeStarWarsRepository : StarWarsRepository {

    override suspend fun getCharacters(): List<Character> = listOf(
        Character("Luke Skywalker", "172", "77", "male")
    )

    override suspend fun getCharacter(
        id: Int
    ): Character = Character("Luke Skywalker", "172", "77", "male")
}