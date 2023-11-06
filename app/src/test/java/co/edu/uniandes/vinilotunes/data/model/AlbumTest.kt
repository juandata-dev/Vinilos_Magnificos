package co.edu.uniandes.vinilotunes.data.model


import co.edu.uniandes.vinilotunes.data.model.Album
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.mockk
import java.util.Date

class AlbumTest {

    private lateinit var album: Album

    @BeforeEach
    fun setUp() {
        album = Album(
            id = 1,
            name = "Album Title",
            cover = "Cover URL",
            genre = "Rock",
            releaseDate = Date(),
            recordLabel = "Label",
            description = "Album Description",
            performers = null,
            tracks = null
        )
    }

    @Test
    fun `test getId() returns the correct ID`() {
        val expectedId = 1
        val actualId = album.id
        assert(expectedId == actualId)
    }

    @Test
    fun `test getTitle() returns the correct title`() {
        val expectedTitle = "Album Title"
        val actualTitle = album.name
        assert(expectedTitle == actualTitle)
    }

    @Test
    fun `test getCover() returns the correct cover URL`() {
        val expectedCover = "Cover URL"
        val actualCover = album.cover
        assert(expectedCover == actualCover)
    }

    // Add more test cases for other properties and methods of the Album class
}
