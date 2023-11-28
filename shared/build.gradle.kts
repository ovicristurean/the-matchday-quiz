plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.20"
    alias(libs.plugins.cocoapods)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(libs.moko.mvvm.core)
        }
    }

    cocoapods {
        version = "1.0"
        summary = "Kotlin Multiplatform shared module for MatchdayQuiz app."
        homepage = "https://github.com/ovicristurean/the-matchday-quiz"
        name = "shared"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../theMatchdayQuizIos/Podfile")

        framework {
            baseName = "shared"
            isStatic = true
            transitiveExport = false
            embedBitcode(org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE)
        }

        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
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
                implementation(libs.koin.android)
            }
        }
        val androidUnitTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
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