package co.edu.uniandes.vinilotunes.data.repositories

import android.app.Application
import android.util.Log
import co.edu.uniandes.vinilotunes.data.model.Collector

import co.edu.uniandes.vinilotunes.data.net.ApiService

class CollectorRepository (val application: Application) {

    //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
    suspend fun getAllCollectors() = ApiService.getAllCollectors()

    suspend fun getAlbumesByCollector(id: Int) = ApiService.getAlbumesByIdCollector(id)

    suspend fun getCollectorById(id: Int): Collector? {
        val collector = ApiService.getCollectorById(id)
        return collector
    }

}