package co.edu.uniandes.vinilotunes.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Clase base para los test de la aplicación que contiene métodos comunes
 *
 */
open class BaseTest {

    /**
     * Metodo que retorna un [Matcher] para un item de un [RecyclerView] en una posición especifica
     *
     * @param position posición del item
     * @param itemMatcher es un [Matcher] para el item
     * @return [Matcher] para el item
     */
    protected fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return itemMatcher.matches(viewHolder?.itemView)
            }
        }
    }


}