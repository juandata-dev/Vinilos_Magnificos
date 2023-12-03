package co.edu.uniandes.vinilotunes.ui.track

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.Track
import co.edu.uniandes.vinilotunes.databinding.FragmentAddTrackBinding
import co.edu.uniandes.vinilotunes.ui.album.AlbumDetailViewModel
import co.edu.uniandes.vinilotunes.ui.album.AlbumViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [AddTrackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTrackFragment : Fragment() {

    private lateinit var binding: FragmentAddTrackBinding
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var albumDetailViewModel: AlbumDetailViewModel
    private var albumId: Int? = null




    /**
     * Metodo para crear la jerarquía de vistas asociada con el fragmento.
     *
     * @param inflater El objeto LayoutInflater que se puede usar para inflar
     * cualquier vista en el fragmento.
     * @param container Si no es nulo, este es el padre de las vistas del fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     * a partir de un estado guardado anterior como se indica aquí.
     * @return Devuelve la vista raíz de la jerarquía de vistas asociada con el fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_track, container, false
            )
        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumDetailViewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        albumId = requireArguments().getInt("album_id")
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

        // Se asigna el listener al botón de crear
        binding.btnCreate.setOnClickListener { onAddTrackPressed(view) } // Se asigna el listener al botón de crear

        binding.btnCancel.setOnClickListener { onCancelPressed() }  // Se asigna el listener al botón de cancelar
    }

    private fun onAddTrackPressed(view : View) {

        if (binding.etNameTrack.text.toString() == "" || binding.etMinutes.text.toString() == "" || binding.etSeconds.text.toString() == "") {
            printSnackBar(view, "Please fill the empty fields", 2000)
        } else {
            val track = Track(
                null, binding.etNameTrack.text.toString(), null,
                binding.etMinutes.text.toString() + ":" + binding.etSeconds.text.toString()
            )
            albumViewModel.addTrackAlbum(albumId.toString(), track)

            printSnackBar(view, "Song ${track.name} added successfully to the album", 2000)


            albumViewModel.trackAdded.observe(viewLifecycleOwner) { track ->
                if (track != null && track.id != 0) {
                    printSnackBar(
                        view,
                        "Track called " + binding.etNameTrack.text + " was added successfully",
                        2000
                    )
                    val bundle = Bundle()
                    bundle.putInt("album_id",albumId!!) // Aquí debes proporcionar el valor del ID del álbum
                    bundle.putInt("update", 1)
                    albumDetailViewModel.getAlbumByIdApi(albumId!!) // Actualizar el cache
                    albumDetailViewModel.album.observe(viewLifecycleOwner) { album ->
                        Log.d("DEBUG", "AddTrackFragment Recibiendo album")
                        if (album != null) { // Los detalles del álbum están disponibles
                            Log.d("DEBUG", "AddTrackFragment Album recibido: $album")
                        }
                    }
                    findNavController().popBackStack()
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.nav_album_detail, bundle)

                } else {
                    printSnackBar(view, "Error adding track " + binding.etNameTrack.text, 2000)
                }
            }
        }
    }

    /**
     * Función que se encarga de imprimir un mensaje en un SnackBar.
     * @param view La vista raíz del fragmento que se ha creado.
     * @param message El mensaje que se desea mostrar.
     * @param duration La duración del mensaje.
     */
    private fun printSnackBar(view: View, message: String, duration: Int) {
        Snackbar.make(
            view,
            message,
            duration
        ).show()
    }

    /**
     * Función que se encarga de manejar el evento de presionar el botón de retroceso.
     */
    private fun onCancelPressed() {
        val bundle = Bundle()
        bundle.putInt("album_id", albumId!!) // Aquí debes proporcionar el valor del ID del álbum
        findNavController().popBackStack()
        findNavController().popBackStack()
        findNavController().navigate(R.id.nav_album_detail, bundle)
    }
}