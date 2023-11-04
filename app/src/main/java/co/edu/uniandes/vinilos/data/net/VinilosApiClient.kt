package co.edu.uniandes.vinilotunes.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val VINILOTUNES_BASE_URL = "http://miso.denkitronik.com:3000"

/**
 * Objeto que facilita la conexión con el servidor de Vinilos a través de Retrofit.
 * Proporciona acceso a la API del servidor y utiliza Retrofit para generar implementaciones de métodos
 * relacionados con álbumes y otros recursos.
 *
 * @property apiRetrofit Objeto Retrofit que permite la comunicación con el servidor de Vinilos.
 * @property albums Objeto que permite acceder a los métodos de la API relacionados con álbumes.
 *
 * Este objeto es un componente clave para realizar solicitudes y obtener datos relacionados con la música.
 * Utiliza la URL base de la API de Vinilos y el convertidor Gson para manejar las respuestas del servidor.
 * La interfaz [AlbumApi] se utiliza para acceder a métodos específicos de álbumes.
 *
 * @see [Retrofit](https://square.github.io/retrofit/)
 * @see [AlbumApi]
 */
object VinilosApiClient {
    /**
     * Objeto que permite la conexión con el servidor de Vinilos.
     * Este objeto es utilizado por Retrofit para generar la implementación de los métodos.
     * Este objeto utiliza el convertidor Gson para convertir los datos de la respuesta del servidor a objetos de Kotlin.
     * Este objeto utiliza la URL base de la API de Vinilos.
     * @see [Retrofit](https://square.github.io/retrofit/)
     */
    private val apiRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(VINILOTUNES_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Objeto que permite acceder a los métodos de la API de albumes.
     * @see [AlbumApi]
     */
    public val albums: AlbumApi = apiRetrofit.create(AlbumApi::class.java)

}