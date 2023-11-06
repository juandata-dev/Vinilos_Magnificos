package co.edu.uniandes.vinilotunes.ui.album

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar los datos del detalle de un álbum en la aplicación.
 * Esta clase es la encargada de manejar la comunicación con el servidor para obtener los datos de un álbum.
 *
 * @param application La aplicación Android que proporciona contexto.
 */
class AlbumDetailViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData es una clase que se utiliza para notificar a las vistas cuando los datos cambian.
    // En este caso, el valor de la variable es un álbum.
    val album = MutableLiveData<Album>()

    // Variable que almacena el repositorio de álbumes.
    // El repositorio es el encargado de manejar la comunicación con el servidor.
    private val albumsRepository = AlbumRepository(application)

    /**
     * Recupera los detalles de un álbum específico a través del repositorio y actualiza el LiveData [album].
     *
     * @param id El ID del álbum que se desea recuperar.
     */
    fun getAlbumById(id: Int) {
        try {
            // Corutina que se ejecuta en un hilo secundario.
            viewModelScope.launch(Dispatchers.IO) {
                album.postValue(albumsRepository.getAlbumById(id)) // Se obtienen los detalles del álbum y se actualiza el valor de la variable.
            }
        } catch (e: Exception) { // Si ocurre un error, se muestra un mensaje en el Logcat.
            Log.e("Error", e.message ?: "Failure service")
        }
    }
}