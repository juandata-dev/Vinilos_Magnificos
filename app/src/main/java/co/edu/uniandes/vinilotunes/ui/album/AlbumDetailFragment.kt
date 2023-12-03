package co.edu.uniandes.vinilotunes.ui.album

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.databinding.FragmentAlbumDetailBinding

/**
 * Fragmento encargado de mostrar los detalles de un álbum específico.
 *
 * Este fragmento se encarga de cargar y mostrar los detalles de un álbum identificado por su ID.
 * Los detalles del álbum se obtienen a través de un ViewModel y se actualizan en la vista
 * una vez que están disponibles.
 *
 * @property albumDetailViewModel ViewModel para obtener y almacenar los detalles del álbum.
 * @property binding El objeto de enlace de datos para actualizar la vista con los detalles del álbum.
 */
class AlbumDetailFragment : Fragment() {

    private lateinit var albumDetailViewModel: AlbumDetailViewModel
    private lateinit var binding: FragmentAlbumDetailBinding


    /**
     * Crea la vista de este fragmento y realiza la inicialización de los componentes.
     *
     * @param inflater El inflador de diseño que se utiliza para inflar la vista.
     * @param container El contenedor padre al que se debe adjuntar la vista (si existe).
     * @param savedInstanceState Información sobre el estado previo del fragmento (si existe).
     * @return La vista raíz del fragmento, que es la vista principal de la jerarquía de vistas.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_album_detail, container, false
        )

        // Configura ViewModel
        albumDetailViewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        return binding.root
    }


    /**
     * Se llama cuando la vista del fragmento se ha creado y es visible para el usuario.
     *
     * @param view La vista raíz del fragmento que se ha creado.
     * @param savedInstanceState Información sobre el estado previo del fragmento (si existe).
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera el ID del álbum del argumento
        val albumId = arguments?.getInt("album_id")
        val update = arguments?.getInt("update")

        // Verifica que el ID no sea nulo y luego carga los detalles del álbum
        if (albumId != null) {

            Log.d("DEBUG", "AlbumDetailFragment Album ID: $albumId")

            if (update == 1) {
                albumDetailViewModel.getAlbumByIdApi(albumId)
            } else {
                albumDetailViewModel.getAlbumById(albumId)
            }

            // Observa el LiveData para recibir los detalles del álbum
            albumDetailViewModel.album.observe(viewLifecycleOwner) { album ->
                if (album != null) { // Los detalles del álbum están disponibles
                    Log.d("DEBUG", "AlbumDetailFragment Album recibido: $album")
                    binding.album = album  // Actualiza la vista con los detalles del álbum
                    binding.fabAddTrack.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putInt(
                            "album_id",
                            albumId
                        ) // Aquí debes proporcionar el valor del ID del álbum
                        findNavController().navigate(R.id.nav_add_track, bundle)
                    }
                }
            }
        }
    }
}
