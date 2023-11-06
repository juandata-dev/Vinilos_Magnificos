package co.edu.uniandes.vinilotunes.data.model

import java.util.Date

/**
 * Clase que representa a un intérprete musical.
 *
 * @param id El identificador único del intérprete.
 * @param name El nombre del intérprete.
 * @param image La URL de la imagen del intérprete.
 * @param birthDate La fecha de nacimiento del intérprete.
 * @param description Una descripción del intérprete.
 * @param albums La lista de álbumes asociados al intérprete.
 */
data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val birthDate: Date,
    val description: String,
    val albums: List<Album>
)