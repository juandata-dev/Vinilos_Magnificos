package co.edu.uniandes.vinilotunes.ui.album

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.Performer
import co.edu.uniandes.vinilotunes.data.model.Track
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


const val DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd"

/**
 * Método personalizado de BindingAdapter para establecer el texto formateado de una fecha en un TextView.
 *
 * @param textView El TextView en el que se mostrará la fecha formateada.
 * @param date La fecha que se formateará y mostrará en el TextView.
 */
@BindingAdapter("app:dateFormat")
fun setFormattedDateText(textView: TextView, date: Date?) {

    val dateFormat = SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.getDefault())
    date?.let {
        textView.text = dateFormat.format(it)
    }
}

/**
 * BindingAdapter personalizado que carga una imagen en un ImageView desde una URL utilizando Glide.
 *
 * @param view El ImageView al que se debe cargar la imagen.
 * @param url La URL de la imagen que se cargará en el ImageView.
 */
@BindingAdapter("app:imageUrl")
fun setImageByUrl(view: ImageView, url: String?) {
    url?.let {
        // Utiliza la referencia `view` en lugar de `binding.albumImage`
        Glide.with(view.context)
            .load(url) // Utiliza la URL pasada como parámetro, no album.cover
            .into(view) // Utiliza la vista pasada como parámetro
    }
}

/**
 * BindingAdapter personalizado que muestra la lista de intérpretes en un TextView.
 *
 * @param view El TextView en el que se mostrarán los intérpretes.
 * @param list La lista de intérpretes que se debe mostrar en el TextView.
 */
@BindingAdapter("app:performersFromList")
fun setPerformers(view: TextView, list: List<Performer>?) {
    list?.let { performers ->
        var artist = ""
        performers.forEach {
            artist += it.name + "\n"
        }
        if (artist.isNotEmpty())
            view.text = artist.substring(0, artist.length - 1)
        else
            view.text = view.context.getString(R.string.activity_album_detail_no_performers)
    }
}

/**
 * BindingAdapter personalizado que muestra la lista de canciones en un TextView.
 *
 * @param view El TextView en el que se mostrarán las canciones.
 * @param list La lista de canciones que se debe mostrar en el TextView.
 */
@BindingAdapter("app:tracksFromList")
fun setTracks(view: TextView, list: List<Track>?) {
    list?.let { tracks ->
        var songs = ""
        tracks.forEach {
            songs += "- " + it.name + ". " + it.duration + "\n"
        }
        if (songs.isNotEmpty())
            view.text = songs.substring(0, songs.length - 1)
        else
            view.text = view.context.getString(R.string.activity_album_no_tracks)
    }
}