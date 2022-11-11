package uk.co.conjure.test_robots

interface RobotContext<G : GivenContext, W : WhenContext, T : ThenContext> {
    val givenContext: G
    val whenContext: W
    val thenContext: T

    val robotState: RobotState
    fun moveToState(robotState: RobotState)
}