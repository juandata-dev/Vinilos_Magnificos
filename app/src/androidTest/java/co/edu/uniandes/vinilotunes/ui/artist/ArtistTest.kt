package co.edu.uniandes.vinilotunes.ui.artist

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.edu.uniandes.vinilotunes.MainActivity
import co.edu.uniandes.vinilotunes.R
import co.edu.uniandes.vinilotunes.base.BaseTest
import org.hamcrest.Matchers.*
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


/**
 * Clase que prueba la funcionalidad de la pantalla de artistas
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ArtistTest : BaseTest() {


    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java) // Es la actividad que se va a probar

    /**
     * Prueba que se carguen los artistas y que se pueda ver el detalle de uno de ellos
     */
    @Test
    fun givenArtists_WhenArtistIsLoad_CheckDetail() {

        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_artists)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        val recyclerView = onView(
            allOf(withId(R.id.rvArtist))
        )

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val textView = onView(
            allOf(
                withId(R.id.tvArtistName),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }

    /**
     * Metodo que prueba que se carguen los artistas y se pruebe que al menos uno exista
     *
     */
    @Test
    fun givenArtists_WhenArtistsIsLoad_CheckAtLeastOne() {
        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_artists)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.rvArtist))
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())))
    }

    /**
     * Metodo que prueba que se carguen los artistas y se pruebe que al menos uno no exista
     *
     */
    @Test
    fun givenArtists_WhenArtistsIsNotLoaded_CheckAtLeastOneNotExits() {
        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_artists)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.rvArtist))
            .check(matches(atPosition(100, not(isDisplayed()))))
    }


}
