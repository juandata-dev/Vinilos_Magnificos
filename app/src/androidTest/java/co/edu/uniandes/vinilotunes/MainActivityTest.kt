package co.edu.uniandes.vinilotunes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Prueba la navegación de la aplicación desde el drawer de navegación
 *
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    /**
     * Prueba que exista el drawer de navegación
     */
    @Test
    fun appLaunchesSuccessfully() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }

    /**
     * Prueba que abre el drawer de navegación y verifica la navegación
     */
    @Test
    fun openDrawer_andVerifyNavigation() {
        // Abre el drawer de navegación
        onView(withId(R.id.drawer_layout)).perform(click())

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        // Verifica si un ítem del menú está presente
        onView(withId(R.id.nav_album)).check(matches(isDisplayed()))

        // Haz clic en el ítem del menú y verifica la navegación
        onView(withId(R.id.nav_artists)).perform(click())

        onView(withId(R.id.nav_artists)).check(matches(isDisplayed()))

    }
}