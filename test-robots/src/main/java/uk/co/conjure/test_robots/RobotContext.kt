package uk.co.conjure.test_robots

/**
 * A wrapper for a set of context objects that specify given, when and then functions for a robot,
 * and the current state of that robot.
 *
 * @see [BaseRobot] for an implementation
 *
 * A testing robot is typically used for specifying how to perform actions and assertions.
 * The order of those actions and assertions is typically specified inside a test:
 *
 * @see [BaseTest]
 *
 * A robot must specify its Given, When and Then context's. These are objects that provide functions
 * to be run at different points in a test. You can then use the [GIVEN], [WHEN] and [THEN] extension
 * functions on a robot to gain access to those contexts in an idiomatic way like so:
 *
 * robot.apply {
 *     GIVEN { the_app_has_just_been_opened() }
 *     WHEN { the_user_presses_the_button() }
 *     THEN { the_message_is_visible() }
 * }
 *
 * A robot has a state as defined by [RobotState]. The robot will start in the [RobotState.GIVEN]
 * state and move to different states when the extension functions [GIVEN], [WHEN] and [THEN] are used.
 *
 * This tracking of state is there to ensure that tests do not move back to a [RobotState.GIVEN] state
 * after executing a [WHEN] or [THEN]. For example the following test would throw a runtime exception
 * when the second [GIVEN] is called:
 *
 * robot.apply {
 *     GIVEN { the_app_has_just_been_opened() }
 *     WHEN { the_user_presses_the_button() }
 *     THEN { the_message_is_visible() }
 *     GIVEN { the_user_is_logged_in() }
 *     THEN { the_username_is_visible() }
 * }
 *
 */
interface RobotContext<G : GivenContext, W : WhenContext, T : ThenContext> {
    val givenContext: G
    val whenContext: W
    val thenContext: T

    val robotState: RobotState
    fun moveToState(robotState: RobotState)
}