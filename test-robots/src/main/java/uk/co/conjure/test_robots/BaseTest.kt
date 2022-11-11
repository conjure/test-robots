package uk.co.conjure.test_robots

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
