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

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun appLaunchesSuccessfully() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun openDrawer_andVerifyNavigation() {
        // Abre el drawer de navegación
        onView(withId(R.id.drawer_layout)).perform(click())

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        // Verifica si un ítem del menú está presente
        onView(withId(R.id.nav_gallery)).check(matches(isDisplayed()))

        // Haz clic en el ítem del menú y verifica la navegación
        onView(withId(R.id.nav_gallery)).perform(click())
        // Aquí puedes añadir más verificaciones después de la navegación
    }
}