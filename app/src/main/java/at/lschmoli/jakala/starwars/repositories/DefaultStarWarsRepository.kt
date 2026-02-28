package at.lschmoli.jakala.starwars.repositories

import at.lschmoli.jakala.starwars.network.ApiService
import at.lschmoli.jakala.starwars.models.Character
import javax.inject.Inject

class DefaultStarWarsRepository @Inject constructor(
    private val api: ApiService
) : StarWarsRepository {
    override suspend fun getCharacters(): List<Character> = api.getCharacters().results
    override suspend fun getCharacter(id: Int) = api.getCharacter(id)
}