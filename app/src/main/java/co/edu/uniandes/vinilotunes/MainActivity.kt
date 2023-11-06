package co.edu.uniandes.vinilotunes

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.edu.uniandes.vinilotunes.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


/**
 * La clase `MainActivity` es la actividad principal de la aplicación y es responsable de gestionar
 * la interfaz de usuario y la navegación entre fragmentos. La actividad se inicia al abrir la
 * aplicación y contiene la barra de herramientas en la parte superior y el menú lateral de
 * navegación.
 */
class MainActivity : AppCompatActivity() {

    // Variables que se utilizan en toda la clase
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /**
     * Método llamado cuando se crea la actividad. Infla la vista de la actividad, configura la barra de
     * herramientas, obtiene referencias a elementos visuales definidos en XML, establece el controlador
     * de navegación y configura la sincronización de la barra de aplicaciones y el menú lateral con el controlador.
     *
     * @param savedInstanceState El estado previamente guardado de la actividad (si existe).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar (crear) la vista de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la barra de herramientas en la parte superior
        setSupportActionBar(binding.appBarMain.toolbar)

        // Obtener referencias a los elementos visuales definidos en XML
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Encontrar el controlador de navegación que gestiona la navegación entre fragmentos
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Definir configuración de la barra de aplicaciones y el menú lateral
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_gallery, R.id.nav_album, R.id.nav_home, R.id.nav_slideshow
            ), drawerLayout
        )

        // Configurar la barra de herramientas para que se sincronice con el controlador de navegación
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configurar el menú lateral para que se sincronice con el controlador de navegación
        navView.setupWithNavController(navController)
    }

    /**
     * Método que se llama para inflar el menú de la actividad; agrega elementos al menú de la barra de acciones (action bar) si está presente.
     *
     * @param menu El menú de opciones de la barra de acciones.
     * @return Devuelve `true` para indicar que el menú ha sido creado con éxito.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú; esto agrega elementos a la barra de acciones (action bar) si está presente.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * Método que gestiona la acción de navegación hacia arriba (flecha hacia atrás en la barra de aplicaciones).
     *
     * @return Devuelve `true` si la navegación se realizó con éxito, en caso contrario, se maneja por el método predeterminado.
     */
    override fun onSupportNavigateUp(): Boolean {
        // Configuración de navegación hacia arriba (flecha hacia atrás en la barra de aplicaciones)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}