import com.ivy.buildsrc.*

apply<com.ivy.buildsrc.IvyPlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

dependencies {
    api(project(":core:data-model"))
    api(project(":resources"))

    Hilt()
    Kotlin(api = true)
    Coroutines(api = true)
    FunctionalProgramming(api = true)
    Timber(api = true)

    Testing(
        // Prevent circular dependency
        commonTest = false,
        commonAndroidTest = false
    )
}