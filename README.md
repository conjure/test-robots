# Test robots

A small library inspired by [Gherkin](https://cucumber.io/docs/gherkin/) to help with writing more readable scenario tests on Android like so:


```kotlin
@Test
fun pressing_the_button_shows_a_message() {
    demoRobot.apply {
        GIVEN { the_app_has_just_been_opened() }
        THEN { the_greeting_message_is_not_visible() }
        WHEN { the_user_presses_the_button() }
        THEN { the_greeting_message_is_visible() }
    }
}
```

[![](https://jitpack.io/v/conjure/test-robots.svg)](https://jitpack.io/#conjure/test-robots)

## Including the library

First add the following to your project level gradle repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Then add the test-robots dependency for instrumented tests in the module you want to test:

```gradle
dependencies {
	androidTestImplementation 'uk.co.conjure:test-robots:1.0.0-alpha02'
}
```

or 

```gradle
dependencies {
	androidTestImplementation 'com.github.conjure:test-robots:1.0.0-alpha02'
}
```

## Using the library

You can find an example of how to use test-robots in the example instrumented test [here](https://github.com/conjure/test-robots/blob/main/app/src/androidTest/java/uk/co/conjure/testrobots/)

The idea is to separate the test implementation from the test scenario. Start by writing a test scenario that inherits from `BaseTest` like so: 

```kotlin
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : BaseTest() {

    private val demoRobot = demoRobot(this)

    @Test
    fun pressing_the_button_shows_a_message() {
        demoRobot.apply {
            GIVEN { the_app_has_just_been_opened() }
            THEN { the_greeting_message_is_not_visible() }
            WHEN { the_user_presses_the_button() }
            THEN { the_greeting_message_is_visible() }
        }
    }
}
```

Then you can write the actual implementation of the scenario inside a "robot" class that inherits from `BaseRobot` like so: 

```kotlin
class DemoRobot(registry: CleanUpRegistry) : BaseRobot<DemoRobot.GivenRobot,
        DemoRobot.WhenRobot,
        DemoRobot.ThenRobot>(registry) {

    override val givenContext = GivenRobot()
    override val whenContext = WhenRobot()
    override val thenContext = ThenRobot()

    inner class GivenRobot : GivenContext {
        fun the_app_has_just_been_opened() {
            ActivityScenario.launch(DemoActivity::class.java)
                .also { scenario -> doOnCleanUp { scenario.close() } }
                .apply { moveToState(Lifecycle.State.RESUMED) }
        }
    }

    inner class WhenRobot : WhenContext {
        fun the_user_presses_the_button() {
            onView(withId(R.id.btn_press_me)).perform(click())
        }
    }

    inner class ThenRobot : ThenContext {
        fun the_greeting_message_is_not_visible() {
            onView(withId(R.id.tv_message)).check(matches(not(isDisplayed())))
        }

        fun the_greeting_message_is_visible() {
            onView(withId(R.id.tv_message)).check(matches(isDisplayed()))
        }
    }
}

fun demoRobot(test: CleanUpRegistry, func: (DemoRobot.() -> Unit)? = null) =
    DemoRobot(test).apply { func?.invoke(this) }

```
