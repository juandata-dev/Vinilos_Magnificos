package co.edu.uniandes.vinilotunes.ui.artist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Performer
import co.edu.uniandes.vinilotunes.data.repository.AlbumRepository
import co.edu.uniandes.vinilotunes.data.repository.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Esta clase es la encargada de manejar la comunicación con el servidor para obtener los datos de los artistas.
 * @property application La aplicación que se está ejecutando.
 */
class ArtistViewModel(application: Application) : AndroidViewModel(application) {
    // Utilizamos MutableLiveData para poder cambiar su valor dinámicamente. En este caso, el valor de la variable es una lista de artistas.
    val listArtists = MutableLiveData<List<Performer>>()

    // Utilizamos MutableLiveData para poder cambiar su valor dinámicamente. En este caso, el valor de la variable es un álbum.
    val artist = MutableLiveData<Performer>()

    // Variable que almacena el repositorio de artistas. El repositorio es el encargado de manejar la comunicación con el servidor.
    private val artistsRepository = ArtistRepository(application)

    // Variable que almacena el texto que se muestra en la vista. En este caso, el texto es "This is artist Fragment".
    private val _text = MutableLiveData<String>().apply {
        value = "This is artist Fragment"
    }

    // LiveData es una clase que se utiliza para notificar a las vistas cuando los datos cambian. En este caso, el valor de la variable es un texto.
    val text: LiveData<String> = _text


    /**
     * Función que obtiene todos los artistas. Esta función se ejecuta en un hilo secundario.
     */
    fun getArtists() {
        try {
            viewModelScope.launch(Dispatchers.IO) {// Corutina que se ejecuta en un hilo secundario.
                listArtists.postValue(artistsRepository.getAllArtists()) // Se obtienen los artistas y se actualiza el valor de la variable.
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

    /**
     * Función que obtiene un artista por su id. Esta función se ejecuta en un hilo secundario.
     *
     * @param id El id del artista que se quiere obtener.
     */
    fun getArtistById(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                artist.postValue(artistsRepository.getArtistById(id))
            }
        } catch (e: Exception) {
            Log.e("Error", e.message ?: "Failure service")
        }
    }

}