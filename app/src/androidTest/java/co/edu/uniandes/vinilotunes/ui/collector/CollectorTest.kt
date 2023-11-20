package co.edu.uniandes.vinilotunes.ui.collector

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilotunes.base.BaseTest
import co.edu.uniandes.vinilotunes.MainActivity
import co.edu.uniandes.vinilotunes.R
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

/**
 * Clase  que prueba la funcionalidad de la pantalla de coleccionista
 *
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorTest : BaseTest() {


    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java) // Es la actividad que se va a probar

    /**
     * Metodo que prueba que se carguen los coleccionistas y que se pueda ver el detalle de uno de ellos
     */
    @Test
    fun givenArtists_WhenArtistIsLoad_List() {

        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_collector)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val recyclerView = onView(
            allOf(withId(R.id.rvCollector))
        )
        recyclerView.check(matches(isDisplayed()))

    }

    /**
     * Metodo que prueba que se carguen los coleccionistas y se comprueba que al menos uno de ellos existe
     *
     */
    @Test
    fun givenCollector_WhenCollectorIsLoad_CheckAtLeastOne() {
        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_collector)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(2))
        onView(withId(R.id.rvCollector))
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())))
    }

    /**
     * Metodo que prueba que se carguen los coleccionistas y se comprueba que al menos uno de ellos no existe
     *
     */
    @Test
    fun givenCollector_WhenCollectorIsNotLoaded_CheckAtLeastOneNotExits() {
        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_collector)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.rvCollector))
            .check(matches(atPosition(100, not(isDisplayed()))))
    }
}
