package co.edu.uniandes.vinilotunes.ui.collector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.databinding.FragmentCollectorBinding

class CollectorFragment : Fragment() {


    // Esta linea es la encargada de hacer el binding con el ViewModel.
    // Un ViewModel es una clase  que se encarga de manejar la lógica de negocio de la vista.
    private var _binding: FragmentCollectorBinding? =
        null // Binding para acceder a los componentes de la vista. Devuelve una vista de álbum o null si no existe.

    // Binding para acceder a los componentes de la vista. get es un método que devuelve una vista de álbum o null si no existe.
    private val binding get() = _binding!!

    // Esta linea devuelve una instancia del ViewModel de Coleccionista. viewModels() es un método que devuelve una instancia del ViewModel de Coleccionista.
    private val collectorViewModel: CollectorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño de la vista del fragmento desde el archivo fragment_album.xml
        // y lo asigna como la vista de este fragmento.
        _binding = FragmentCollectorBinding.inflate(layoutInflater)

        // Obtiene una referencia a la vista raíz y la devuelve.
        val root: View = binding.root

        // Crea un adaptador para la lista de Coleccionistas.
        val collectorAdapter = CollectorAdapter()

        // Asigna el manejador de eventos de selección de un Coleccionista.
        collectorAdapter.onCollectorSelected = this::goToDetailCollector

        // Asigna el adaptador a la lista de Coleccionistas.
        binding.rvCollector.adapter = collectorAdapter

        // Observa el evento de selección de un Coleccionista.
        collectorViewModel.listCollectors.observe(viewLifecycleOwner) {
            collectorAdapter.collectorList = it // Actualiza la lista de álbumes.
        }

        return root // Devuelve la vista raíz.
    }


    /**
     * Método que se llama cuando el fragmento se ha hecho visible.
     */
    override fun onResume() {
        super.onResume()
        collectorViewModel.getCollectors()
    }

    /**
     * Método que se llama cuando el fragmento se destruye.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    /**
     * Metodo que se encarga de navegar a la vista de detalle de un Coleccionista.
     *
     * @param id El id del Coleccionista que fue seleccionado.
     */
    private fun goToDetailCollector(id: Int) {

        val bundle = Bundle()
        bundle.putInt("collector_id", id) // Aquí debes proporcionar el valor del ID del Coleccionista

        findNavController().navigate(R.id.nav_collector_detail, bundle)

    }
}