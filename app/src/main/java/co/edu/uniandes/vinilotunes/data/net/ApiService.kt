package co.edu.uniandes.vinilotunes.data.net

import co.edu.uniandes.vinilotunes.data.model.Album

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

    }
}