package co.edu.uniandes.vinilotunes.data.repository

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Track
import co.edu.uniandes.vinilotunes.data.net.ApiService
import co.edu.uniandes.vinilotunes.data.net.CacheManager
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
    suspend fun getAllAlbums(): List<Album>? {
    //suspend fun getAllAlbums(): List<Album> = ApiService.getAllAlbums()
        return try {
            val cacheManager = CacheManager.getInstance(application.applicationContext)
            val cachedAlbumList = cacheManager.getAlbumList()

            if (!cachedAlbumList.isNullOrEmpty()) {
                // Si la lista de álbumes está en caché, la retorna
                Log.d("DEBUG", "Returning Album list from cache")
                cachedAlbumList
            } else {
                // Si no está en caché, se carga desde el servidor
                Log.d("DEBUG", "Fetching from network for Album list")
                val albums = ApiService.getAllAlbums() ?: emptyList()
                cacheManager.setAlbumList(albums) // Se agrega la lista de álbumes a la caché local
                albums
            }
        } catch (e: Exception) {
            Log.e("Error fetching Album list", e.message ?: "Unknown error")
            null
        }
    }
    suspend fun getAlbumById(id: Int): Album? {
        return try {
            CacheManager.getInstance(application.applicationContext).getAlbum(id) //Carga de la caché local
                ?: run { //Si no está en caché, se carga desde el servidor
                    Log.d("DEBUG", "Fetching from network for Album with id $id") //Se imprime un mensaje en el log
                    val album = ApiService.getAlbumById(id) //Se obtiene el álbum desde el servidor
                    CacheManager.getInstance(application.applicationContext).addAlbum(id, album) //Se agrega el álbum a la caché local
                    album   //Se retorna el álbum
                }
        } catch (e: Exception) {
            Log.e("Error fetching Album", e.message ?: "Unknown error") //Se imprime un mensaje en el log
            null
        }
    }

    suspend fun addTrackAlbum(id: String, track: Track) = ApiService.addTrackAlbum(id, track)
    fun invalidateCache() {
        Log.d("DEBUG", "Invalidating cache")
        CacheManager.getInstance(application.applicationContext).invalidateCache()
    }

    /**
     * Método que se encarga de obtener todos los álbumes desde el servidor.
     * @return La lista de álbumes.
     */
    suspend fun getAllAlbumsFromApi(): List<Album>? {
        //suspend fun getAllAlbums(): List<Album> = ApiService.getAllAlbums()
        return try {
            val cacheManager = CacheManager.getInstance(application.applicationContext)
            val cachedAlbumList = cacheManager.getAlbumList()
            Log.d("DEBUG", "Fetching from network for Album list")
            val albums = ApiService.getAllAlbums() ?: emptyList()
            Log.d("DEBUG", "Returning Album list from API")
            cacheManager.setAlbumList(albums) // Se agrega la lista de álbumes a la caché local
            albums
        } catch (e: Exception) {
            Log.e("Error fetching Album list", e.message ?: "Unknown error")
            null
        }
    }

    suspend fun getAlbumByIdApi(id: Int): Album? {
        return try {
                    Log.d("DEBUG", "getAlbumByIdApi Fetching from network for Album with id $id") //Se imprime un mensaje en el log
                    val album = ApiService.getAlbumById(id) //Se obtiene el álbum desde el servidor
                    CacheManager.getInstance(application.applicationContext).delAlbum()
                    CacheManager.getInstance(application.applicationContext).addAlbum(id, album) //Se agrega el álbum a la caché local
                    album   //Se retorna el álbum
        } catch (e: Exception) {
            Log.e("Error fetching Album", e.message ?: "Unknown error") //Se imprime un mensaje en el log
            null
        }

    }

}