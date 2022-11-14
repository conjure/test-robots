package uk.co.conjure.test_robots

/**
 * The base class for a test that specifies scenarios. A [BaseTest] is a [CleanUpRegistry] meaning
 * that it keeps a hold of a list of tasks to be performed for clean up when the test is complete.
 *
 * You must however call [cleanUp] your self e.g.
 *
 * @RunWith(AndroidJUnit4::class)
 * class ExampleInstrumentedTest : BaseTest() {
 *
 *   private val demoRobot = demoRobot(this)
 *
 *   @Test
 *   fun pressing_the_button_shows_a_message() {
 *      demoRobot.apply {
 *          GIVEN { the_app_has_just_been_opened() }
 *          THEN { the_greeting_message_is_not_visible() }
 *          WHEN { the_user_presses_the_button() }
 *          THEN { the_greeting_message_is_visible() }
 *      }
 *   }

 *   @After
 *   fun tearDown() = cleanUp()
 * }
 *
 */
abstract class BaseTest : CleanUpRegistry {

    private val cleanUpTasks = mutableListOf<() -> Unit>()

    override fun registerCleanUpTask(func: () -> Unit) {
        this.cleanUpTasks.add(func)
    }

    /**
     * Call [cleanUp] from your @After annotated function
     */
    fun cleanUp() {
        cleanUpTasks.forEach { runTask ->
            try {
                runTask()
            } catch (ignore: Exception) {

            }
        }
    }
}
