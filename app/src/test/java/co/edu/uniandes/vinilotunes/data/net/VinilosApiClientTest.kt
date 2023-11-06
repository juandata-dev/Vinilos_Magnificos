package co.edu.uniandes.vinilotunes.data.net

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VinilosApiClientTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: AlbumApi

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Use mock server
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(AlbumApi::class.java)
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchAlbumsAndVerifyResponse() = runBlocking {
        // Define a simulated response
        val responseBody = """
        [
            {
                "id": 1,
                "name": "Album 1",
                "cover": "http://example.com/album1cover.jpg",
                "genre": "Rock",
                "releaseDate": "2023-01-01T00:00:00Z",
                "recordLabel": "Label 1",
                "description": "Description of Album 1",
                "tracks": null,
                "performers": null
            },
            {
                "id": 2,
                "name": "Album 2",
                "cover": "http://example.com/album2cover.jpg",
                "genre": "Pop",
                "releaseDate": "2023-02-01T00:00:00Z",
                "recordLabel": "Label 2",
                "description": "Description of Album 2",
                "tracks": null,
                "performers": null
            }
        ]
    """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        // Call the API
        val response = apiService.getAllAlbums()

        // Assertions to verify the response
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())

    }
}