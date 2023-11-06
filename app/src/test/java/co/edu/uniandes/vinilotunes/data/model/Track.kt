package co.edu.uniandes.vinilotunes.data.model

import co.edu.uniandes.vinilotunes.data.model.Track
import co.edu.uniandes.vinilotunes.data.model.Album
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.mockk

class TrackTest {

    private lateinit var track: Track

    @BeforeEach
    fun setUp() {
        val mockAlbum = mockk<Album> {
            every { id } returns 1
            every { name } returns "Album Title"
        }

        track = Track(
            id = 1,
            name = "Track Name",
            album = mockAlbum,
            duration = "3:30"
        )
    }

    @Test
    fun `test getId() returns the correct ID`() {
        val expectedId = 1
        val actualId = track.id
        assert(expectedId == actualId)
    }

    @Test
    fun `test getName() returns the correct name`() {
        val expectedName = "Track Name"
        val actualName = track.name
        assert(expectedName == actualName)
    }

    @Test
    fun `test getAlbum() returns the correct album`() {
        val expectedAlbumTitle = "Album Title"
        val actualAlbumTitle = track.album?.name
        assert(expectedAlbumTitle == actualAlbumTitle)
    }

    @Test
    fun `test getDuration() returns the correct duration`() {
        val expectedDuration = "3:30"
        val actualDuration = track.duration
        assert(expectedDuration == actualDuration)
    }

    // Add more test cases for other properties and methods of the Track class
}
