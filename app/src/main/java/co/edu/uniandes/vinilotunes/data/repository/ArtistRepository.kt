package co.edu.uniandes.vinilotunes.data.repository

import android.app.Application
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Performer
import co.edu.uniandes.vinilotunes.data.net.ApiService

/**
 * Esta clase es la encargada de manejar la comunicación entre la aplicación y el servidor
 * para la entidad [Performer]
 *
 * @property application Aplicación desde la cual se está ejecutando el repositorio
 */
class ArtistRepository (val application: Application) {

    /**
     * Metodo encargado de obtener todos los artistas
     *
     * @return [List]  Lista de artistas
     */
    suspend fun getAllArtists(): List<Performer> = ApiService.getAllArtist()

    
    /**
     * Metodo encargado de obtener un artista por su id
     *
     * @param id Id del artista a obtener
     * @return [Performer? or null] Artista con el id especificado
     */
    suspend fun getArtistById(id: Int): Performer? {
        val artist = ApiService.getArtistById(id)
        return artist
    }
}