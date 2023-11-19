package co.edu.uniandes.vinilotunes.data.repository

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Performer
import co.edu.uniandes.vinilotunes.data.net.ApiService
import co.edu.uniandes.vinilotunes.data.net.CacheManager

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
    suspend fun getAllArtists(): List<Performer>? {
        return try {
            val cacheManager = CacheManager.getInstance(application.applicationContext)
            val cachedArtistList = cacheManager.getArtistList()

            if (!cachedArtistList.isNullOrEmpty()) {
                // Si la lista de álbumes está en caché, la retorna
                Log.d("DEBUG", "Returning Artist list from cache")
                cachedArtistList
            } else {
                // Si no está en caché, se carga desde el servidor
                val artists = ApiService.getAllArtist() ?: emptyList()
                cacheManager.setArtistList(artists) // Se agrega la lista de álbumes a la caché local
                artists
            }
        } catch (e: Exception) {
            Log.e("Error fetching Album list", e.message ?: "Unknown error")
            null
        }
    }


    
    /**
     * Metodo encargado de obtener un artista por su id
     *
     * @param id Id del artista a obtener
     * @return [Performer? or null] Artista con el id especificado
     */
    suspend fun getArtistById(id: Int): Performer? {
        return try {
            CacheManager.getInstance(application.applicationContext).getArtist(id) //Carga de la caché local
                ?: run { //Si no está en caché, se carga desde el servidor
                    Log.d("DEBUG", "Fetching from network for Artist with id $id") //Se imprime un mensaje en el log
                    val artist = ApiService.getArtistById(id) //Se obtiene el artista desde el servidor
                    CacheManager.getInstance(application.applicationContext).addArtist(id, artist) //Se agrega el artista a la caché local
                    artist   //Se retorna el álbum
                }
        } catch (e: Exception) {
            Log.e("Error fetching Album", e.message ?: "Unknown error") //Se imprime un mensaje en el log
            null
        }
    }
}