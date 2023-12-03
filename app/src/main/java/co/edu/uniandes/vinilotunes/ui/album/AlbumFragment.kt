package co.edu.uniandes.vinilotunes.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.databinding.FragmentAlbumBinding

/**
 * Clase que representa el Fragmento de Album.
 * Esta clase es la encargada de manejar la vista de álbumes.
 * @return Devuelve una vista de álbum.
 */
class AlbumFragment : Fragment() {

    // Esta linea es la encargada de hacer el binding con el ViewModel.
    // Un ViewModel es una clase  que se encarga de manejar la lógica de negocio de la vista.
    private var _binding: FragmentAlbumBinding? =
        null // Binding para acceder a los componentes de la vista. Devuelve una vista de álbum o null si no existe.

    // Binding para acceder a los componentes de la vista. get es un método que devuelve una vista de álbum o null si no existe.
    private val binding get() = _binding!!

    // Esta linea devuelve una instancia del ViewModel de álbum. viewModels() es un método que devuelve una instancia del ViewModel de álbum.
    private val albumViewModel: AlbumViewModel by viewModels()


    /**
     * Método que se llama cuando el fragmento ha creado su vista.
     * @param inflater El LayoutInflater que se puede utilizar para inflar cualquier vista.
     * @param container Si no es nulo, este es el padre al que se deben adjuntar las vistas.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de
     * un estado guardado anterior como se indica aquí.
     * @return Devuelve la vista raíz del fragmento, es decir, la vista raíz de la jerarquía de
     * vistas del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño de la vista del fragmento desde el archivo fragment_album.xml
        // y lo asigna como la vista de este fragmento.
        _binding = FragmentAlbumBinding.inflate(layoutInflater)

        // Obtiene una referencia a la vista raíz y la devuelve.
        val root: View = binding.root

        // Crea un adaptador para la lista de álbumes.
        val albumAdapter = AlbumAdapter()

        albumAdapter.onAlbumSelected = this::goToDetailAlbum

        // Asigna el adaptador a la lista de álbumes.
        binding.rvAlbums.adapter = albumAdapter

        // Observa el evento de selección de un álbum.
        albumViewModel.listAlbums.observe(viewLifecycleOwner) {
            albumAdapter.albumList = it // Actualiza la lista de álbumes.
        }

        binding.fabAddTrack.setOnClickListener {
            val bundle = Bundle()
            findNavController().navigate(R.id.nav_add_album, bundle)
        }

        return root // Devuelve la vista raíz.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val update = arguments?.getInt("update")

        if (update == 1) {
            albumViewModel.getAlbumsFromApi()
        } else {
            albumViewModel.getAlbums()
        }

    }


    /**
     * Método que se llama cuando el fragmento se ha hecho visible.
     */
    override fun onResume() {
        super.onResume()
        val update = arguments?.getInt("update")

        if (update == 1) {
            albumViewModel.getAlbumsFromApi()
        } else {
            albumViewModel.getAlbums()
        }
    }

    /**
     * Método que se llama cuando el fragmento se destruye.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToDetailAlbum(id: Int) {

        val bundle = Bundle()
        bundle.putInt("album_id", id) // Aquí debes proporcionar el valor del ID del álbum

        findNavController().navigate(R.id.nav_album_detail, bundle)

    }

}
