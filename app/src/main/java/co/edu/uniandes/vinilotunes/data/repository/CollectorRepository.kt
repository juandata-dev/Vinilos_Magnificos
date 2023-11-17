package co.edu.uniandes.vinilotunes.data.repositories

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Collector
import co.edu.uniandes.vinilotunes.data.net.ApiService

class CollectorRepository (val application: Application) {

    /**
     * Método que se encarga de obtener todos los colleccionistas desde el servidor.
     * @return La lista de coleccionistas.
     */
    suspend fun getAllCollector(): List<Collector> = ApiService.getAllCollector()
    suspend fun getCollectorById(id: Int): Collector? {
        val collector = ApiService.getCollectorById(id)
        return collector
    }

}