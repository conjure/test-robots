package uk.co.conjure.test_robots

import org.junit.Assert

/**
 * A context for a set of functions that specify how to set up "Given" assumptions in a scenario test
 */
interface GivenContext

/**
 * A context for a set of functions that specify how to execute "When" steps in a scenario test
 */
interface WhenContext

/**
 * A context for a set of functions that specify how to perform "Then" assertions in a scenario test
 */
interface ThenContext

/**
 * An extension function on a [RobotContext] that takes a function to be executed inside the context
 * of the robots [GivenContext]. If the robot is not in a [RobotState.GIVEN] state then a runtime
 * exception will be thrown.
 */
fun <R : RobotContext<G, W, T>, G : GivenContext, W : WhenContext, T : ThenContext> R.GIVEN(func: G.() -> Unit) {
    Assert.assertEquals(
        "Can not execute a GIVEN from a ${robotState.printName} context",
        RobotState.GIVEN,
        robotState
    )
    func(givenContext)
}

/**
 * An extension function on a [RobotContext] that takes a function to be executed inside the context
 * of the robots [WhenContext]. Executing a [WHEN] will move the robot to a [RobotState.WHEN] state.
 */
fun <R : RobotContext<G, W, T>, G : GivenContext, W : WhenContext, T : ThenContext> R.WHEN(func: W.() -> Unit) {
    moveToState(RobotState.WHEN)
    func(whenContext)
}

/**
 * An extension function on a [RobotContext] that takes a function to be executed inside the context
 * of the robots [ThenContext]. Executing a [THEN] will move the robot to a [RobotState.THEN] state.
 */
fun <R : RobotContext<G, W, T>, G : GivenContext, W : WhenContext, T : ThenContext> R.THEN(func: T.() -> Unit) {
    moveToState(RobotState.THEN)
    func(thenContext)
}
