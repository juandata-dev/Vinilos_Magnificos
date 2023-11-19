package co.edu.uniandes.vinilotunes.data.repositories

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Collector
import co.edu.uniandes.vinilotunes.data.model.Performer

import co.edu.uniandes.vinilotunes.data.net.ApiService
import co.edu.uniandes.vinilotunes.data.net.CacheManager

class CollectorRepository (val application: Application) {

    /**
     * Metodo que retorna la lista de todos los coleccionistas
     * Si la lista está en caché, la retorna, de lo contrario la carga desde el servidor
     * Si ocurre un error, retorna null
     * @return [List] Lista de coleccionistas
     */
    suspend fun getAllCollectors(): List<Collector>? {
        return try {
            val cacheManager = CacheManager.getInstance(application.applicationContext)
            val cachedCollectorList = cacheManager.getCollectorList()

            if (!cachedCollectorList.isNullOrEmpty()) {
                // Si la lista de colecionistas está en caché, la retorna
                Log.d("DEBUG", "Returning Collector list from cache")
                cachedCollectorList
            } else {
                // Si no está en caché, se carga desde el servidor
                val collectors = ApiService.getAllCollectors() ?: emptyList()
                cacheManager.setCollectorList(collectors) // Se agrega la lista de coleccionistas a la caché local
                collectors
            }
        } catch (e: Exception) {
            Log.e("Error fetching Collector list", e.message ?: "Unknown error")
            null
        }
    }


    /**
     * Metodo que retorna un coleccionista por su id
     * Si el coleccionista está en caché, lo retorna, de lo contrario lo carga desde el servidor
     *
     * @param id Id del coleccionista a obtener
     * @return [Collector? or null] Coleccionista con el id especificado
     */
    suspend fun getAlbumesByCollector(id: Int) = ApiService.getAlbumesByIdCollector(id)

    suspend fun getCollectorById(id: Int): Collector? {
        val collector = ApiService.getCollectorById(id)
        return collector
    }

}