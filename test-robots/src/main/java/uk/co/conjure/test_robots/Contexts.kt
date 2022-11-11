package uk.co.conjure.test_robots

import org.junit.Assert

interface GivenContext
interface WhenContext
interface ThenContext

fun <R : RobotContext<G, W, T>, G : GivenContext, W : WhenContext, T : ThenContext> R.GIVEN(func: G.() -> Unit) {
    Assert.assertEquals(
        "Can not execute a GIVEN from a ${robotState.printName} context",
        RobotState.GIVEN,
        robotState
    )
    func(givenContext)
}

fun <R : RobotContext<G, W, T>, G : GivenContext, W : WhenContext, T : ThenContext> R.WHEN(func: W.() -> Unit) {
    moveToState(RobotState.WHEN)
    func(whenContext)
}

fun <R : RobotContext<G, W, T>, G : GivenContext, W : WhenContext, T : ThenContext> R.THEN(func: T.() -> Unit) {
    moveToState(RobotState.THEN)
    func(thenContext)
}
