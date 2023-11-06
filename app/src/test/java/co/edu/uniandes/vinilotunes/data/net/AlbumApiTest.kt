package co.edu.uniandes.vinilotunes.data.net

import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Track

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.Date

class AlbumApiTest {

    private lateinit var albumApi: AlbumApi

    @BeforeEach
    fun setUp() {
        albumApi = mockk()
    }

    @Test
    fun `getAllAlbums returns list of albums`() = runTest {
        // Prepara la respuesta simulada
        val mockResponse = listOf(Album(1, "Album 1", "coverUrl", "Rock", Date(), "Label", "Description", null, null))
        val response = Response.success(mockResponse)

        // Configura el comportamiento simulado
        coEvery { albumApi.getAllAlbums() } returns response

        // Llama al método y verifica el resultado
        val result = albumApi.getAllAlbums()
        assertEquals(response, result)
    }

    @Test
    fun `getAlbumById returns specific album`() = runTest {
        // Preparar respuesta simulada
        val mockAlbum = Album(1, "Album 1", "coverUrl", "Rock", Date(), "Label", "Description", null, null)
        val response = Response.success(mockAlbum)

        // Configurar comportamiento simulado
        coEvery { albumApi.getAlbumById("1") } returns response

        // Llamar al método y verificar el resultado
        val result = albumApi.getAlbumById("1")
        assertEquals(response, result)
    }

    @Test
    fun `insertAlbum adds new album and returns it`() = runTest {
        // Preparar álbum para insertar y respuesta simulada
        val newAlbum = Album(null, "New Album", "newCoverUrl", "Pop", Date(), "NewLabel", "NewDescription", null, null)
        val response = Response.success(newAlbum)

        // Configurar comportamiento simulado
        coEvery { albumApi.insertAlbum(newAlbum) } returns response

        // Llamar al método y verificar el resultado
        val result = albumApi.insertAlbum(newAlbum)
        assertEquals(response, result)
    }

    @Test
    fun `addTrackToAlbum adds track to album and returns it`() = runTest {
        // Preparar un álbum y track para agregar y respuesta simulada
        val mockAlbum = Album(1, "Album 1", "coverUrl", "Rock", Date(), "Label", "Description", null, null)
        val newTrack = Track(null, "New Track", mockAlbum, "3:45")
        val response = Response.success(newTrack)

        // Configurar comportamiento simulado
        coEvery { albumApi.addTrackToAlbum("1", newTrack) } returns response

        // Llamar al método y verificar el resultado
        val result = albumApi.addTrackToAlbum("1", newTrack)
        assertEquals(response, result)
    }
}