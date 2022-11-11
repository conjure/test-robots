package uk.co.conjure.test_robots

interface CleanUpRegistry {
    fun registerCleanUpTask(func: () -> Unit)
}