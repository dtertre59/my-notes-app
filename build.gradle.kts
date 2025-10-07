plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    id("com.rickclephas.kmp.nativecoroutines").version("1.0.0-ALPHA-47").apply(false)

    // Room
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.androidx.room) apply false
}