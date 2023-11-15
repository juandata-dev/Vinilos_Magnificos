package co.edu.uniandes.vinilotunes.ui.artist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.databinding.FragmentArtistsDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ArtistsDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistsDetailsFragment : Fragment() {

    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var binding: FragmentArtistsDetailsBinding


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
            inflater, R.layout.fragment_artists_details, container, false
        )

        // Configura ViewModel
        artistViewModel = ViewModelProvider(this).get(ArtistViewModel::class.java)

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

        // Recupera el ID del artista del argumento
        val artistId = arguments?.getInt("artist_id")

        // Verifica que el ID no sea nulo y luego carga los detalles del artista
        if (artistId != null) {

            Log.d("Debug", "ArtistDetailFragment Artist ID: $artistId"   )

            // Utiliza el ID del artista para cargar los detalles del artista desde el repositorio
            artistViewModel.getArtistById(artistId)

            // Observa el LiveData para recibir los detalles del artista
            artistViewModel.artist.observe(viewLifecycleOwner) { artist ->
                if (artist != null) { // Los detalles del artista están disponibles
                    binding.artist = artist  // Actualiza la vista con los detalles del artista
                }
            }
        }
    }
}