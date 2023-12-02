package co.edu.uniandes.vinilotunes.ui.collector

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.data.model.Album
import co.edu.uniandes.vinilotunes.data.model.CollectorAlbum
import co.edu.uniandes.vinilotunes.databinding.FragmentCollectorDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CollectorDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CollectorDetailFragment : Fragment() {
    private lateinit var collectorViewModel: CollectorViewModel
    private lateinit var binding: FragmentCollectorDetailBinding
    private var collectorAlbumAdapter = CollectorAlbumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_collector_detail, container, false
        )

        collectorViewModel = ViewModelProvider(this)[CollectorViewModel::class.java] // Configura ViewModel
        binding.rvCollectorAlbums.adapter = collectorAlbumAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera el ID del collecionista del argumento
        val collectorId = arguments?.getInt("collector_id")

        // Verifica que el ID no sea nulo y luego carga los detalles del álbum
        if (collectorId != null) {

            Log.d("Debug", "CollectorDetailFragment Collector ID: $collectorId"   )

            collectorViewModel.getCollectorById(collectorId)

            // Observa el LiveData para recibir los detalles del collecionista
            collectorViewModel.collector.observe(viewLifecycleOwner) { collector ->
                if (collector != null) { // Los detalles del coleccionistas están disponibles
                    binding.collector = collector  // Actualiza la vista con los detalles del álbum
                    Log.d("Debug", "getAlbumesByID: $collector.id"   )
                    collectorViewModel.getAlbumesByIdCollector(collector.id)
                }
            }

            collectorViewModel.albumesCollector.observe(viewLifecycleOwner) { collectorAlbumsList ->
                val albumes: MutableList<Album> = mutableListOf()

                for (i in collectorAlbumsList.indices) {
                    albumes.add(collectorAlbumsList[i].album)
                }
                if (albumes.isNotEmpty()) {
                    binding.rvCollectorAlbums.visibility = View.VISIBLE
                    binding.tvLabelNoAlbums.visibility = View.GONE

                    collectorAlbumAdapter.collectorAlbumList = collectorAlbumsList
                    binding.rvCollectorAlbums.adapter = collectorAlbumAdapter
                    Log.d("Debug", "myAlbums: ${collectorAlbumAdapter.collectorAlbumList[0].album.cover}")

                } else {
                    binding.rvCollectorAlbums.visibility = View.GONE
                    binding.tvLabelNoAlbums.visibility = View.VISIBLE
                }

            }
        }


    }

    override fun onResume() {
        super.onResume()
    }

    private fun goToDetailAlbum(id: Int) {
        val bundle = Bundle()
        bundle.putInt("album_id", id) // Aquí debes proporcionar el valor del ID del álbum

        findNavController().navigate(R.id.nav_album_detail, bundle)
    }
}