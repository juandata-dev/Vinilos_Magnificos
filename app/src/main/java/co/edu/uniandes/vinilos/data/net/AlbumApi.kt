package co.edu.uniandes.vinilotunes.data.net

import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.Track
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface que define los métodos para acceder a la API de álbumes de Vinilos.
 * Esta interface es utilizada por Retrofit para generar la implementación de los métodos.
 * Los métodos de esta interface son suspendidos para que puedan ser llamados desde un hilo de ejecución diferente al hilo principal.
 * Los métodos de esta interface retornan objetos de tipo Response que contienen los datos de la respuesta del servidor.
 * Los métodos de esta interface son utilizados por la clase [ApiService].
 */
interface AlbumApi {

    /**
     * Método que permite obtener todos los álbumes.
     * @return Un objeto de tipo Response que contiene una lista de álbumes.
     */
    @GET("albums")
    suspend fun getAllAlbums() : Response<List<Album>>

    /**
     * Método que permite obtener un álbum por su id.
     * @param id El id del álbum.
     * @return Un objeto de tipo Response que contiene el álbum.
     */
    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: String) : Response<Album>

    /**
     * Método que permite insertar un álbum.
     * @param album El álbum a insertar.
     * @return Un objeto de tipo Response que contiene el álbum insertado.
     */
    @POST("albums")
    suspend fun insertAlbum(@Body album: Album) : Response<Album>

    /**
     * Método que permite agregar una canción a un álbum.
     * @param id El id del álbum.
     * @param track La canción a agregar.
     * @return Un objeto de tipo Response que contiene la canción agregada.
     */
    @POST("albums/{id}/tracks")
    suspend fun addTrackToAlbum(@Path("id") id: String, @Body track: Track) : Response<Track>
}