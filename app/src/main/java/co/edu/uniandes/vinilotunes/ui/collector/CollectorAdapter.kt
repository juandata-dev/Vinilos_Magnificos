package co.edu.uniandes.vinilotunes.ui.collector

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.Collector
import co.edu.uniandes.vinilotunes.databinding.ItemCollectorLayoutBinding

class CollectorAdapter: RecyclerView.Adapter<CollectorAdapter.CollectorHolder>() {

    var onCollectorSelected: ((id: Int) -> Unit)? =
        null // Esta linea se encarga de inicializar el manejador de eventos de selección de un artista.
    // En caso de que no se seleccione un artista, no se devuelve nada. Si se selecciona un artista, se devuelve el id del artista seleccionado.


    // Este método se encarga de actualizar la lista de álbumes. Se llama cada vez que se actualiza la lista de álbumes.
    var collectorList: List<Collector> =
        listOf()  // Esta linea se encarga de inicializar la lista de álbumes.
        @SuppressLint("NotifyDataSetChanged") // Esta linea se encarga de suprimir el lint warning de NotifyDataSetChanged.
        set(value) { // Este método se encarga de actualizar la lista de álbumes.
            field = value // Esta linea se encarga de asignar la lista de álbumes.
            notifyDataSetChanged() // Esta linea se encarga de notificar que los datos han cambiado.
        }

    /**
     * Este método se encarga de manejar el evento de selección de un artista.
     * @param position La posición del artista que va a ser seleccionado.
     */
    fun onClickCollector(position: Int) {
        Log.d("CollectorAdapter", "CollectorAdapter: Clic en el collector $position")
        onCollectorSelected?.invoke(collectorList[position].id) // Esta linea se encarga de invocar un artista cuando se selecciona en la lista de artistas.
    }


    /**
     * Este método se encarga de crear un nuevo ViewHolder.
     * Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del RecyclerView.
     * Este método se llama cuando el RecyclerView necesita un nuevo ViewHolder para representar un ítem.
     * @param parent El ViewGroup en el que se añadirá la nueva vista.
     * @param viewType El tipo de vista de la nueva vista.
     * @return Devuelve un nuevo AlbumHolder que contiene una vista de la nueva vista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorHolder {
        val view =
            LayoutInflater.from(parent.context)  // Esta linea se encarga de obtener el contexto de la vista padre.
                .inflate(
                    R.layout.item_collector_layout,
                    parent,
                    false
                ) // Infla el diseño de la vista del ítem desde el archivo item_artist_layout.xml
        return CollectorHolder(view) // Devuelve un nuevo AlbumHolder que contiene una vista de la nueva vista.

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
    override fun onBindViewHolder(holder: CollectorHolder, position: Int) {
        holder.bind(
            collectorList[position],
            position,
            this
        ) // Este método se encarga de actualizar el contenido de un ViewHolder.
    }

    /**
     * Este método se encarga de obtener el número de ítems en el conjunto de datos del adaptador.
     * @return Devuelve el número de ítems en el conjunto de datos del adaptador.
     */
    override fun getItemCount() = collectorList.size

    /**
     * Esta clase representa un ViewHolder que contiene una vista de un ítem.
     * Un ViewHolder describe una vista de un ítem y metadatos sobre su posición dentro del RecyclerView.
     * @param view La vista de un ítem.
     */
    class CollectorHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Este valor representa el enlace de datos de la vista de un ítem.
        // Los !! se utilizan para indicar que el valor no puede ser nulo.
        private val binding: ItemCollectorLayoutBinding = DataBindingUtil.bind(view)!!


        /**
         * Este método se encarga de enlazar los datos de un artista con la vista de un ítem.
         *
         * @param artist El artista que se va a enlazar con la vista de un ítem.
         * @param position La posición del artista que se va a enlazar con la vista de un ítem.
         * @param handlerArtistAdapter  El adaptador de artistas que gestiona la vista.
         */
        fun bind(collector: Collector, position: Int, handlerCollectorAdapter: CollectorAdapter) {
            binding.collector = collector
            binding.position = position
            binding.collectorAdapter = handlerCollectorAdapter
        }

    }
}