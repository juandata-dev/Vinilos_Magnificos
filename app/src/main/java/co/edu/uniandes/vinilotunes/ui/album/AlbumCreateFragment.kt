package co.edu.uniandes.vinilotunes.ui.album

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.databinding.FragmentAlbumBinding
import co.edu.uniandes.vinilotunes.databinding.FragmentAlbumCreateBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumCreateFragment : Fragment() {
    private lateinit var albumViewModel: AlbumViewModel
    private val calendar = Calendar.getInstance()
    private lateinit var datePicker: DatePickerDialog
    private var _binding: FragmentAlbumCreateBinding? =
        null // Binding para acceder a los componentes de la vista. Devuelve una vista de álbum o null si no existe.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumCreateBinding.inflate(layoutInflater)

        // Obtiene una referencia a la vista raíz y la devuelve.
        val root: View = binding.root

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se asigna el listener al botón de crear
        binding.btnCreate.setOnClickListener { onAddAlbumPressed(view) } // Se asigna el listener al botón de crear

        binding.btnCancel.setOnClickListener { onCancelPressed() }  // Se asigna el listener al botón de cancelar


        binding.etReleaseDate.setOnClickListener {
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            datePicker = DatePickerDialog(requireActivity(), { _, mYear, mMonth, dayOfMonth ->
                binding.etReleaseDate.setText(
                    getString(
                        R.string.fragment_album_create_date_format,
                        dayOfMonth,
                        mMonth + 1,
                        mYear
                    )
                )
            }, year, month, day)
            datePicker.show()
        }

        binding.btnCreate.setOnClickListener {onAddAlbumPressed(view)}

    }

    private fun printSnackBar(view: View, message: String, duration: Int) {
        Snackbar.make(
            view,
            message,
            duration
        ).show()
    }

    private fun onCancelPressed() {
        val bundle = Bundle()
        findNavController().navigate(R.id.nav_album, bundle)
    }

    private fun onAddAlbumPressed(view: View) {

        if (binding.etName.text.toString() == "" || binding.etCover.text.toString() == "" ||
            binding.etDescription.text.toString() == "" || binding.etReleaseDate.text.toString() == "" ||
            binding.spGenre.selectedItemId == 0L || binding.spLabel.selectedItemId == 0L
        ) {
            printSnackBar(view, "There are empty fields. Please fill all fields", 2000)
        } else {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
            val releaseDate: Date = try {
                sdf.parse(binding.etReleaseDate.text.toString())!!
            } catch (e: Exception) {
                Date()
            }
            val album = Album(
                null,
                binding.etName.text.toString(),
                binding.etCover.text.toString(),
                binding.spGenre.selectedItem.toString(),
                releaseDate,
                binding.spLabel.selectedItem.toString(),
                binding.etDescription.text.toString(),
                null,
                null
            )
            albumViewModel.insertAlbum(album)
            printSnackBar(view, "Album created successfully", 2000)
            val bundle = Bundle()
            bundle.putInt("update", 1) // Aquí debes proporcionar el valor del ID del álbum
            findNavController().navigate(R.id.nav_album, bundle)

        }
    }

}