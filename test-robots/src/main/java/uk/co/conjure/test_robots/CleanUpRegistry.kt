package uk.co.conjure.test_robots

/**
 * An object that can register tasks to be performed for clean up.
 *
 * @see [BaseTest]
 */
interface CleanUpRegistry {
    fun registerCleanUpTask(func: () -> Unit)
}