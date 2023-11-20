package co.edu.uniandes.vinilotunes


import co.edu.uniandes.vinilotunes.ui.album.AlbumTest
import co.edu.uniandes.vinilotunes.ui.artist.ArtistTest
import co.edu.uniandes.vinilotunes.ui.collector.CollectorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AlbumTest::class,
    ArtistTest::class,
    CollectorTest::class
)
class MainTestSuite
