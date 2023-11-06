package co.edu.uniandes.vinilotunes.data.model

import java.util.*

/**
 * Clase que representa a un álbum musical.
 *
 * @param id El identificador único del álbum (puede ser nulo).
 * @param name El nombre del álbum.
 * @param cover La URL de la portada del álbum.
 * @param genre El género musical al que pertenece el álbum.
 * @param releaseDate La fecha de lanzamiento del álbum.
 * @param recordLabel El sello discográfico del álbum.
 * @param description Una descripción del álbum.
 * @param tracks La lista de pistas (canciones) del álbum (puede ser nula).
 * @param performers La lista de intérpretes asociados al álbum (puede ser nula).
 */
data class Album(
    val id: Int?,
    val name: String,
    val cover: String,
    val genre: String,
    val releaseDate: Date,
    val recordLabel: String,
    val description: String,
    val tracks: List<Track>?,
    val performers: List<Performer>?
)