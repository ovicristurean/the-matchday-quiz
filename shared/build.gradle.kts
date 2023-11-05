plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.20"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(libs.moko.mvvm.core)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.moko.mvvm.core)
                implementation(libs.moko.mvvm.compose)
                implementation(libs.moko.mvvm.flow)
                implementation(libs.moko.mvvm.flow.compose)

                //navigation
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.tab.navigator)

                //image loading
                api(libs.image.loader)

                implementation(project(":quiz"))
                implementation(project(":widgets"))
                implementation(project(":splash"))

                //gitlive
                implementation(libs.firebase.firestore)
                implementation(libs.firebase.common)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.gitlive.firebase.auth)

                //koin
                api(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activity.compose)
                implementation(libs.firebase.facebook)
                implementation(libs.play.services.auth)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
    }
}

android {
    namespace = "com.ovidiucristurean.thematchdayquiz"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(project(mapOf("path" to ":quiz")))
    commonMainApi(libs.moko.mvvm.core)
    commonMainApi(libs.moko.mvvm.compose)
    commonMainApi(libs.moko.mvvm.flow)
    commonMainApi(libs.moko.mvvm.flow.compose)
}