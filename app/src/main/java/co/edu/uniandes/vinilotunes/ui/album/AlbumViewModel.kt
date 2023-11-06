package co.edu.uniandes.vinilotunes.ui.album

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Esta clase es la encargada de manejar la comunicación con el servidor para obtener los datos de los álbumes.
 * @property application La aplicación que se está ejecutando.
 */
class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    // Utilizamos MutableLiveData para poder cambiar su valor dinámicamente. En este caso, el valor de la variable es una lista de álbumes.
    val listAlbums = MutableLiveData<List<Album>>()

    // Utilizamos MutableLiveData para poder cambiar su valor dinámicamente. En este caso, el valor de la variable es un álbum.
    val album = MutableLiveData<Album>()

    // Variable que almacena el repositorio de álbumes. El repositorio es el encargado de manejar la comunicación con el servidor.
    private val albumsRepository = AlbumRepository(application)

    // Variable que almacena el texto que se muestra en la vista. En este caso, el texto es "This is album Fragment".
    private val _text = MutableLiveData<String>().apply {
        value = "This is album Fragment"
    }

    // LiveData es una clase que se utiliza para notificar a las vistas cuando los datos cambian. En este caso, el valor de la variable es un texto.
    val text: LiveData<String> = _text


    /**
     * Función que obtiene todos los álbumes. Esta función se ejecuta en un hilo secundario.
     */
    fun getAlbums() {
        try {
            viewModelScope.launch(Dispatchers.IO) {// Corutina que se ejecuta en un hilo secundario.
                listAlbums.postValue(albumsRepository.getAllAlbums()) // Se obtienen los álbumes y se actualiza el valor de la variable.
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

    fun getAlbumById(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                album.postValue(albumsRepository.getAlbumById(id))
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }
}