package uk.co.conjure.testrobots

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.not
import uk.co.conjure.test_robots.*

class DemoRobot(registry: CleanUpRegistry) : BaseRobot<DemoRobot.GivenRobot,
        DemoRobot.WhenRobot,
        DemoRobot.ThenRobot>(registry) {

    override val givenContext = GivenRobot()
    override val whenContext = WhenRobot()
    override val thenContext = ThenRobot()

    inner class GivenRobot : GivenContext {
        fun the_app_has_just_been_opened() {
            ActivityScenario.launch(DemoActivity::class.java)
                .also { scenario -> doOnCleanUp { scenario.close() } }
                .apply { moveToState(Lifecycle.State.RESUMED) }
        }
    }

    inner class WhenRobot : WhenContext {
        fun the_user_presses_the_button() {
            onView(withId(R.id.btn_press_me)).perform(click())
        }
    }

    inner class ThenRobot : ThenContext {
        fun the_greeting_message_is_not_visible() {
            onView(withId(R.id.tv_message)).check(matches(not(isDisplayed())))
        }

        fun the_greeting_message_is_visible() {
            onView(withId(R.id.tv_message)).check(matches(isDisplayed()))
        }
    }
}

fun demoRobot(test: CleanUpRegistry, func: (DemoRobot.() -> Unit)? = null) =
    DemoRobot(test).apply { func?.invoke(this) }
