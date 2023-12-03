package co.edu.uniandes.vinilotunes.data.net

import android.content.Context
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Collector
import co.edu.uniandes.vinilotunes.data.model.Performer

class CacheManager private constructor(context: Context) {


    private val performers: HashMap<Int, Performer> = hashMapOf()
    private val artistList: List<Performer> = mutableListOf()

    private val albums: HashMap<Int, Album> = hashMapOf()
    private val albumList: List<Album> = mutableListOf()

    private val collectors: HashMap<Int, Collector> = hashMapOf()
    private val collectorList: List<Collector> = mutableListOf()

    // Métodos para artista

    /**
     * Agrega una lista de artistas a la lista de artistas
     *
     * @param artistList Lista de artistas a agregar
     */
    fun setArtistList(artists: List<Performer>) {
        (artistList as MutableList<Performer>).clear()
        Log.d("DEBUG", "Artist list: $artists")
        artistList.addAll(artists)
    }


    /**
     * Obtiene la lista de artistas
     *
     * @return [List] Lista de artistas
     */
    fun getArtistList(): List<Performer>? = artistList.toList()


    /**
     * Agrega un artista a la lista de artistas
     *
     * @param performerId Identificador del artista
     * @param performer Artista a agregar
     */
    fun addArtist(performerId: Int, performer: Performer?) {
        performer?.let { performers.putIfAbsent(performerId, it) }
    }

    /**
     * Obtiene un artista de la lista de artistas
     *
     * @param performerId Identificador del artista
     * @return [Performer?] Artista con el id especificado
     */
    fun getArtist(performerId: Int): Performer? = performers[performerId]



    // Métodos para álbum

    /**
     * Agrega una lista de álbumes a la lista de álbumes
     *
     * @param albums Lista de álbumes a agregar
     */
    fun setAlbumList(albums: List<Album>) {
        (albumList as MutableList<Album>).clear()
        albumList.addAll(albums)
    }

    /**
     * Obtiene la lista de álbumes
     *
     * @return [List] Lista de álbumes
     */
    fun getAlbumList(): List<Album>? = albumList.toList()

    /**
     * Agrega un álbum a la lista de álbumes
     *
     * @param albumId Identificador del álbum
     * @param album Álbum a agregar
     */
    fun addAlbum(albumId: Int, album: Album?) {
        album?.let { albums.putIfAbsent(albumId, it) }
    }

    /**
     * Obtiene un álbum de la lista de álbumes
     *
     * @param albumId Identificador del álbum
     * @return [Album?] Álbum con el id especificado
     */
    fun getAlbum(albumId: Int): Album? = albums[albumId]


    // Métodos para coleccionista

    /**
     * Agrega un coleccionista a la lista de coleccionistas
     *
     * @param collectorId Identificador del coleccionista
     * @param collector Coleccionista a agregar
     */
    fun addCollector(collectorId: Int, collector: Collector?) {
        collector?.let { collectors.putIfAbsent(collectorId, it) }
    }

    /**
     * Obtiene un coleccionista de la lista de coleccionistas
     *
     * @param collectorId Identificador del coleccionista
     * @return [Collector?] Coleccionista con el id especificado
     */
    fun getCollector(collectorId: Int): Collector? = collectors[collectorId]


    /**
     * Agrega una lista de coleccionistas a la lista de coleccionistas
     *
     * @param collectors Lista de coleccionistas a agregar
     */
    fun setCollectorList(collectors: List<Collector>) {
        (collectorList as MutableList<Collector>).clear()
        collectorList.addAll(collectors)
    }


    /**
     * Obtiene la lista de coleccionistas
     *
     * @return [List] Lista de coleccionistas
     */
    fun getCollectorList(): List<Collector>? = collectorList.toList()

    /**
     * Invalida la caché de albumes
     */
    fun invalidateCache() {
        (albumList as MutableList<Album>).clear()
    }

    fun delAlbum() {
        albums.clear()
    }

    fun delAlbums() {
        (albumList as MutableList<Album>).clear()
    }


    /**
     *  Singleton para la clase CacheManager
     */
    companion object {
        @Volatile
        private var instance: CacheManager? = null

        fun getInstance(context: Context): CacheManager =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also { instance = it }
            }
    }


}
