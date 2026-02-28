package at.lschmoli.jakala.starwars.repositories

import at.lschmoli.jakala.starwars.network.ApiService
import at.lschmoli.jakala.starwars.util.MainDispatcherRule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@OptIn(ExperimentalSerializationApi::class)
class DefaultStarWarsRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var repository: DefaultStarWarsRepository

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                Json.Default.asConverterFactory("application/json".toMediaType())
            )
            .build()

        repository = DefaultStarWarsRepository(
            retrofit.create(ApiService::class.java)
        )
    }

    @Test
    fun `getPeople returns parsed list`() = runTest {

        val mockResponse = """
            {
              "results": [
                {
                  "name": "Luke Skywalker",
                  "height": "172",
                  "mass": "77",
                  "gender": "male"
                }
              ]
            }
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        val result = repository.getCharacters()
        expectThat(result.first().name) isEqualTo "Luke Skywalker"

        val request = mockWebServer.takeRequest()
        expectThat(request.path) isEqualTo "/people/"
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}