package co.edu.uniandes.vinilotunes.ui.collector

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.CollectorAlbum
import co.edu.uniandes.vinilotunes.databinding.ItemCollectorAlbumBinding

class CollectorAlbumAdapter : RecyclerView.Adapter<CollectorAlbumAdapter.CollectorAlbumHolder>() {

    var onCollectorAlbumSelected: ((id: Int) -> Unit)? = null // Esta linea se encarga de inicializar el manejador de eventos de selección de un album de coleccionista.
    // En caso de que no se seleccione un album de coleccionista, no se devuelve nada. Si se selecciona un album de coleccionista, se devuelve el id del album de coleccionista seleccionado.


    // Este método se encarga de actualizar la lista de álbumes. Se llama cada vez que se actualiza la lista de álbumes.
    var collectorAlbumList: List<CollectorAlbum> =
        listOf()  // Esta linea se encarga de inicializar la lista de álbumes.
        @SuppressLint("NotifyDataSetChanged") // Esta linea se encarga de suprimir el lint warning de NotifyDataSetChanged.
        set(value) { // Este método se encarga de actualizar la lista de álbumes.
            field = value // Esta linea se encarga de asignar la lista de álbumes.
            notifyDataSetChanged() // Esta linea se encarga de notificar que los datos han cambiado.
        }

    /**
     * Este método se encarga de manejar el evento de selección de un album de coleccionista.
     * @param position La posición del album de coleccionista que va a ser seleccionado.
     */
    fun onClickCollectorAlbum(position: Int) {
        Log.d("CollectorAdapter", "CollectorAdapter: Clic en el collector $position")
        onCollectorAlbumSelected?.invoke(collectorAlbumList[position].id) // Esta linea se encarga de invocar un album de coleccionista cuando se selecciona en la lista de album de coleccionistas.
    }


    /**
     * Este método se encarga de crear un nuevo ViewHolder.
     * Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del RecyclerView.
     * Este método se llama cuando el RecyclerView necesita un nuevo ViewHolder para representar un ítem.
     * @param parent El ViewGroup en el que se añadirá la nueva vista.
     * @param viewType El tipo de vista de la nueva vista.
     * @return Devuelve un nuevo AlbumHolder que contiene una vista de la nueva vista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorAlbumHolder {
        val view =
            LayoutInflater.from(parent.context)  // Esta linea se encarga de obtener el contexto de la vista padre.
                .inflate(
                    R.layout.item_collector_album,
                    parent,
                    false
                ) // Infla el diseño de la vista del ítem desde el archivo item_collector_album.xml
        return CollectorAlbumHolder(view) // Devuelve un nuevo AlbumHolder que contiene una vista de la nueva vista.

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
    override fun onBindViewHolder(holder: CollectorAlbumHolder, position: Int) {
        holder.bind(
            collectorAlbumList[position],
            position,
            this
        ) // Este método se encarga de actualizar el contenido de un ViewHolder.
    }

    /**
     * Este método se encarga de obtener el número de ítems en el conjunto de datos del adaptador.
     * @return Devuelve el número de ítems en el conjunto de datos del adaptador.
     */
    override fun getItemCount() = collectorAlbumList.size

    /**
     * Esta clase representa un ViewHolder que contiene una vista de un ítem.
     * Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del RecyclerView.
     * @param view La vista de un ítem.
     */
    class CollectorAlbumHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Este valor representa el enlace de datos de la vista de un ítem.
        // Los !! se utilizan para indicar que el valor no puede ser nulo.
        private val binding: ItemCollectorAlbumBinding = DataBindingUtil.bind(view)!!


        /**
         * Este método se encarga de enlazar los datos de un album de coleccionista con la vista de un ítem.
         *
         * @param artist El album de coleccionista que se va a enlazar con la vista de un ítem.
         * @param position La posición del album de coleccionista que se va a enlazar con la vista de un ítem.
         * @param handleralbum de coleccionistadapter  El adaptador de album de coleccionistas que gestiona la vista.
         */
        fun bind(collectorAlbum: CollectorAlbum, position: Int, handlerCollectorAlbumAdapter: CollectorAlbumAdapter) {
           // binding.collector = collectorAlbum
            binding.tvCollectorAlbumPrice.text = "Price: ${collectorAlbum.price}"
            binding.tvCollectorAlbumStatus.text = "Status: ${collectorAlbum.status}"
            binding.position = position
            binding.collectorAlbumAdapter = handlerCollectorAlbumAdapter
            binding.collectorAlbum = collectorAlbum
        }

    }
}