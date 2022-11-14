package uk.co.conjure.test_robots

/**
 * The base class for a testing robot. Typically an implementation of [BaseTest] will pass its
 * self to the constructor so that the robot and the test both share the same [CleanUpRegistry]
 * of tasks to be run on clean up. e.g.
 *
 * class ExampleInstrumentedTest : BaseTest() {
 *
 *   private val demoRobot = demoRobot(this)
 *
 *   ...
 *
 * }
 *
 * @see [RobotContext] for more information on how to use this class.
 */
abstract class BaseRobot<G : GivenContext, W : WhenContext, T : ThenContext>(
    private val cleanUp: CleanUpRegistry
) : RobotContext<G, W, T> {

    private var _robotState: RobotState = RobotState.GIVEN

    override val robotState: RobotState
        get() = _robotState

    override fun moveToState(robotState: RobotState) {
        _robotState = robotState
    }

    fun doOnCleanUp(task: () -> Unit) = cleanUp.registerCleanUpTask(task)
}