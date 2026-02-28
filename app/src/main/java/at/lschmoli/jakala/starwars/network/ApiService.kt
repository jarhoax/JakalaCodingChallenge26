package at.lschmoli.jakala.starwars.network

import at.lschmoli.jakala.starwars.models.CharacterResponse
import at.lschmoli.jakala.starwars.models.Character
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.GET
import retrofit2.http.Path

@OptIn(InternalSerializationApi::class)
interface ApiService {

    @GET("people/")
    suspend fun getCharacters(): CharacterResponse

    @GET("people/{id}/")
    suspend fun getCharacter(@Path("id") id: Int): Character
}