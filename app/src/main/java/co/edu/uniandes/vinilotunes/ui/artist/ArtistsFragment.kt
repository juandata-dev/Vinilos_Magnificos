package co.edu.uniandes.vinilotunes.ui.artist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.databinding.FragmentAlbumBinding
import co.edu.uniandes.vinilotunes.databinding.FragmentArtistsBinding
import co.edu.uniandes.vinilotunes.ui.album.AlbumAdapter

//TODO Terminar los comentarios de esta clase.
/**
 * A simple [Fragment] subclass.
 * Use the [ArtistsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistsFragment : Fragment() {

    // Esta linea es la encargada de hacer el binding con el ViewModel.
    // Un ViewModel es una clase  que se encarga de manejar la lógica de negocio de la vista.
    private var _binding: FragmentArtistsBinding? =
        null // Binding para acceder a los componentes de la vista. Devuelve una vista de álbum o null si no existe.

    // Binding para acceder a los componentes de la vista. get es un método que devuelve una vista de álbum o null si no existe.
    private val binding get() = _binding!!

    // Esta linea devuelve una instancia del ViewModel de artista. viewModels() es un método que devuelve una instancia del ViewModel de artista.
    private val artistViewModel: ArtistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño de la vista del fragmento desde el archivo fragment_album.xml
        // y lo asigna como la vista de este fragmento.
        _binding = FragmentArtistsBinding.inflate(layoutInflater)

        // Obtiene una referencia a la vista raíz y la devuelve.
        val root: View = binding.root

        // Crea un adaptador para la lista de artistas.
        val artistAdapter = ArtistAdapter()

        // Asigna el manejador de eventos de selección de un artista.
        artistAdapter.onArtistSelected = this::goToDetailArtist

        // Asigna el adaptador a la lista de artistas.
        binding.rvArtist.adapter = artistAdapter

        // Observa el evento de selección de un artista.
        artistViewModel.listArtists.observe(viewLifecycleOwner) {
            artistAdapter.artistList = it // Actualiza la lista de álbumes.
        }

        return root // Devuelve la vista raíz.
    }


    /**
     * Método que se llama cuando el fragmento se ha hecho visible.
     */
    override fun onResume() {
        super.onResume()
        artistViewModel.getArtists()
    }

    /**
     * Método que se llama cuando el fragmento se destruye.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /**
     * Metodo que se encarga de navegar a la vista de detalle de un artista.
     *
     * @param id El id del artista que fue seleccionado.
     */
    private fun goToDetailArtist(id: Int) {

        val bundle = Bundle()
        bundle.putInt("artist_id", id) // Aquí debes proporcionar el valor del ID del artista

        findNavController().navigate(R.id.nav_artist_detail, bundle)

    }

}
