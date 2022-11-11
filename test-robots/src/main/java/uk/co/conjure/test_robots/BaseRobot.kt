package uk.co.conjure.test_robots

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