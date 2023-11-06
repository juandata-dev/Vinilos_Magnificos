package co.edu.uniandes.vinilotunes.ui.album

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.databinding.ItemAlbumLayoutBinding
import com.bumptech.glide.Glide

/**
 * Adapter para la vista de lista de álbumes.
 * Este adapter se utiliza para vincular datos de álbumes a la vista de lista y manejar eventos de selección.
 * @property onAlbumSelected Callback para manejar eventos de selección de álbumes.
 * @property albumList Lista de álbumes que se mostrarán en la vista de lista.
 */
class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    var onAlbumSelected: ((id: Int) -> Unit)? = null // Esta linea se encarga de inicializar el manejador de eventos de selección de un álbum.
    // En caso de que no se seleccione un álbum, no se devuelve nada. Si se selecciona un álbum, se devuelve el id del álbum seleccionado.


    // Este método se encarga de actualizar la lista de álbumes. Se llama cada vez que se actualiza la lista de álbumes.
    var albumList: List<Album> = listOf()  // Esta linea se encarga de inicializar la lista de álbumes.
        @SuppressLint("NotifyDataSetChanged") // Esta linea se encarga de suprimir el lint warning de NotifyDataSetChanged.
        set(value) { // Este método se encarga de actualizar la lista de álbumes.
            field = value // Esta linea se encarga de asignar la lista de álbumes.
            notifyDataSetChanged() // Esta linea se encarga de notificar que los datos han cambiado.
        }

    /**
     * Este método se encarga de manejar el evento de selección de un álbum.
     * @param position La posición del álbum que va a ser seleccionado.
     */
    fun onClickAlbum(position: Int) {
        Log.d("AlbumAdapter", "AlbumAdapter: Clic en el album $position")
        onAlbumSelected?.invoke(albumList[position].id!!) // Esta linea se encarga de invocar un album cuando se selecciona en la lista de álbumes.
    }

    /**
     * Este método se encarga de crear un nuevo ViewHolder.
     * Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del RecyclerView.
     * Este método se llama cuando el RecyclerView necesita un nuevo ViewHolder para representar un ítem.
     * @param parent El ViewGroup en el que se añadirá la nueva vista.
     * @param viewType El tipo de vista de la nueva vista.
     * @return Devuelve un nuevo AlbumHolder que contiene una vista de la nueva vista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view = LayoutInflater.from(parent.context)  // Esta linea se encarga de obtener el contexto de la vista padre.
            .inflate(R.layout.item_album_layout, parent, false) // Infla el diseño de la vista del ítem desde el archivo item_album_layout.xml
        return AlbumHolder(view) // Devuelve un nuevo AlbumHolder que contiene una vista de la nueva vista.

       // Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del
       // RecyclerView vs AlbumHolder que describe una vista de un ítem y metadatos sobre su
       // posición dentro del RecyclerView y contiene un método bind.

    }

    /**
     * Este método se encarga de actualizar el contenido de un ViewHolder.
     * Utiliza el parámetro position para indicar la posición del ítem dentro del conjunto de datos del adaptador.
     * @param holder El ViewHolder que debe actualizarse.
     * @param position La posición del ítem dentro del conjunto de datos del adaptador.
     */
    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bind(albumList[position], position, this) // Este método se encarga de actualizar el contenido de un ViewHolder.
    }

    /**
     * Este método se encarga de obtener el número de ítems en el conjunto de datos del adaptador.
     * @return Devuelve el número de ítems en el conjunto de datos del adaptador.
     */
    override fun getItemCount() = albumList.size

    /**
     * Esta clase representa un ViewHolder que contiene una vista de un ítem.
     * Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del RecyclerView.
     * @param view La vista de un ítem.
     */
    class AlbumHolder(view: View): RecyclerView.ViewHolder(view) {

        // Este valor representa el enlace de datos de la vista de un ítem.
        // Los !! se utilizan para indicar que el valor no puede ser nulo.
        private val binding: ItemAlbumLayoutBinding = DataBindingUtil.bind(view)!!

        /**
         * Este método se encarga de enlazar los datos de un álbum con la vista de un ítem.
         * @param album El álbum que se va a enlazar con la vista de un ítem.
         * @param position La posición del álbum que se va a enlazar con la vista de un ítem.
         * @param handlerAlbumAdapter  El adaptador de álbumes que gestiona la vista.
         */
        fun bind(album: Album, position: Int, handlerAlbumAdapter: AlbumAdapter){
            binding.album = album
            binding.position = position
            binding.albumAdapter = handlerAlbumAdapter

            // Esta linea se encarga de cargar la imagen del álbum en la vista de un ítem,
            // por medio de album.cover que contiene la URL de la imagen.
            // Glide es una librería de Android que se utiliza para cargar imágenes desde una URL.
            Glide.with(binding.root.context)// Esta linea se encarga de obtener el contexto de la vista padre.
                .load(album.cover) // Esta linea se encarga de cargar la imagen del álbum
                .into(binding.albumImage) // Esta linea se encarga de cargar la imagen del álbum en la vista de un ítem.
        }

    }

}