package co.edu.uniandes.vinilotunes.data.net

import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Collector
import co.edu.uniandes.vinilotunes.data.model.CollectorAlbum
import co.edu.uniandes.vinilotunes.data.model.Performer
import co.edu.uniandes.vinilotunes.data.model.Track

/**
 * Clase que proporciona métodos para interactuar con una API que maneja información sobre álbumes musicales.
 * Esta clase se utiliza para realizar solicitudes y obtener datos relacionados con álbumes y su información
 * asociada, como detalles de artistas, pistas de canciones y otros atributos relevantes.
 */
class ApiService {
    /**
     * Objeto que permite acceder a los métodos de la API de álbumes.
     * Esta sección del objeto proporciona acceso a funciones específicas relacionadas con álbumes musicales,
     * como la obtención de todos los álbumes disponibles en la API.
     */
    companion object {
        /**
         * Método que permite obtener todos los álbumes disponibles en la API.
         * Este método realiza una solicitud a la API y recopila la lista de álbumes disponibles. Si la solicitud
         * es exitosa, se devuelve una lista de objetos de álbum, de lo contrario, se devuelve una lista vacía.
         * @return Una lista de álbumes obtenidos de la API.
         */
        suspend fun getAllAlbums(): List<Album> {
            val request = VinilosApiClient.albums.getAllAlbums()
            Log.d("DEBUG", "Returning Album list from API")
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()
        }

        /**
         * Metodo que permite obtener un álbum por su id.
         * Este método realiza una solicitud a la API y recopila el álbum con el id especificado.
         * Si la solicitud es exitosa, se devuelve el álbum, de lo contrario, se devuelve null.
         * @param id El id del álbum que se desea obtener.
         * @return [Album? or null] El álbum obtenido de la API.
         */
        suspend fun getAlbumById (id: Int): Album? {
            val request = VinilosApiClient.albums.getAlbumById(id.toString())
            return if (request.isSuccessful)
                request.body()
            else
                null
        }

        suspend fun getAllArtist(): List<Performer> {
            val request = VinilosApiClient.artist.getAllArtists()
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()
        }

        suspend fun getArtistById(id: Int): Performer? {
            val request = VinilosApiClient.artist.getArtistById(id.toString())
            return if (request.isSuccessful)
                request.body()
            else
                null
        }

        suspend fun getAllCollectors(): List<Collector> {

            val request = VinilosApiClient.collectors.getAllCollectors()
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()

        }

        suspend fun getAlbumesByIdCollector(id: Int): List<CollectorAlbum> {
            val request = VinilosApiClient.collectors.getAlbumsByIdCollector(id.toString())
            return if (request.isSuccessful)
                request.body() ?: listOf()
            else
                listOf()

        }

        suspend fun getCollectorById(id: Int): Collector? {
            val request = VinilosApiClient.collectors.getCollectorById(id.toString())
            return if (request.isSuccessful)
                request.body()
            else
                null

        }

        suspend fun addTrackAlbum(id: String, track: Track): Track? {
            val request = VinilosApiClient.albums.addTrackToAlbum(id, track)
            return if (request.isSuccessful)
                request.body()
            else
                null
        }

    }
}