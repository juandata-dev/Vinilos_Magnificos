package co.edu.uniandes.vinilotunes.data.repository

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.net.ApiService
import co.edu.uniandes.vinilotunes.data.net.VinilosApiClient

/**
 * Esta clase es la encargada de manejar la comunicación con el servidor para obtener los datos de los álbumes.
 * @property application La aplicación que se está ejecutando.
 */
class AlbumRepository (val application: Application) {

    /**
     * Método que se encarga de obtener todos los álbumes desde el servidor.
     * @return La lista de álbumes.
     */
    suspend fun getAllAlbums(): List<Album> = ApiService.getAllAlbums()
    suspend fun getAlbumById(id: Int): Album? {
        val album = ApiService.getAlbumById(id)
        return album
    }

}