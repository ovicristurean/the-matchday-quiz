plugins {
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
    id("com.google.gms.google-services") version "4.4.0" apply false
    //alias(libs.plugins.cocoapods) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
