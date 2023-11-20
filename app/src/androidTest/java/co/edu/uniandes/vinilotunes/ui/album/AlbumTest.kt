package co.edu.uniandes.vinilotunes.ui.album

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
 * Clase que prueba la funcionalidad de la pantalla de albumes
 *
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumTest: BaseTest() {



    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java) // Es la actividad que se va a probar

    /**
     * Prueba que se carguen los albumes y que se pueda ver el detalle de uno de ellos
     */
    @Test
    fun givenAlbum_WhenAlbumIsLoad_CheckDetail() {

        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_album)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.rvAlbums))
            .check(matches(isDisplayed()))
            .perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        val textView = onView(
            allOf(
                withId(R.id.tvGenre),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val label = onView(
            allOf(
                withId(R.id.tracks),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        label.check(matches(withText("Tracks")))
    }

    /**
     * Metodo que prueba que se carguen los albumes y se pruebe que al menos uno exista
     *
     */
    @Test
    fun givenAlbums_WhenAlbumsIsLoad_CheckAtLeastOne() {
        // Abre el drawer de navegación
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_album)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        onView(withId(R.id.rvAlbums))
            .perform(scrollToPosition<ViewHolder>(0))
            .check(matches(atPosition(0, isDisplayed())));
    }


    /**
     * Metodo que prueba que se carguen los albumes y se pruebe que al menos uno no exista
     *
     */
    @Test
    fun givenAlbums_WhenAlbumsIsNotLoaded_CheckAtLeastOneNotExits() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        onView(withId(R.id.nav_album)).perform(click())

        Thread.sleep(TimeUnit.SECONDS.toMillis(1))

        onView(withId(R.id.rvAlbums))
            .check(matches(atPosition(100, not(isDisplayed()))));
    }




}