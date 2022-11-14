package uk.co.conjure.test_robots

/**
 * The state of a robot.
 *
 * @see [RobotContext]
 */
enum class RobotState(val printName: String) {
    GIVEN("GIVEN"),
    WHEN("WHEN"),
    THEN("THEN")
}