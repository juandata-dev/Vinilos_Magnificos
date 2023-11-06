package co.edu.uniandes.vinilotunes.data.model

/**
 * Clase que representa una pista (canción) de un álbum musical.
 *
 * @param id El identificador único de la pista (puede ser nulo).
 * @param name El nombre de la pista.
 * @param album El álbum al que pertenece la pista (puede ser nulo).
 * @param duration La duración de la pista en formato de cadena (por ejemplo, "3:45").
 */
data class Track(
    val id: Int?,
    val name: String,
    val album: Album?,
    val duration: String
)