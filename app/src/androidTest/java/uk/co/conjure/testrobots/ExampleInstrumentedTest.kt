package uk.co.conjure.testrobots

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import uk.co.conjure.test_robots.BaseTest
import uk.co.conjure.test_robots.GIVEN
import uk.co.conjure.test_robots.THEN
import uk.co.conjure.test_robots.WHEN

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : BaseTest() {

    private val demoRobot = demoRobot(this)

    @Test
    fun pressing_the_button_shows_a_message() {
        demoRobot.apply {
            GIVEN { the_app_has_just_been_opened() }
            THEN { the_greeting_message_is_not_visible() }
            WHEN { the_user_presses_the_button() }
            THEN { the_greeting_message_is_visible() }
        }
    }
}