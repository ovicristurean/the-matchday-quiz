plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("io.github.luca992.multiplatform-swiftpackage") version "2.2.0"
    id("app.cash.sqldelight")
}

kotlin {
    androidTarget()

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

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(libs.moko.mvvm.core)
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
                implementation(libs.voyager.koin)

                //image loading
                api(libs.image.loader)

                implementation(libs.kotlinx.serialization.json)

                //gitlive
                implementation(libs.gitlive.firebase.auth)
                implementation(libs.firebase.firestore)

                //koin
                api(libs.koin.core)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                //multiplatform settings
                implementation(libs.multiplatform.settings)
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
                implementation(libs.sql.delight.android)
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

            dependencies {
                implementation(libs.sql.delight.ios)
            }
        }
    }
}

sqldelight {
    databases {
        create("MatchdayDatabase") {
            packageName.set("com.ovidiucristurean.thematchdayquiz")
        }
    }
}

android {
    namespace = "com.ovidiucristurean.thematchdayquiz"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

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
    commonMainApi(libs.moko.mvvm.core)
    commonMainApi(libs.moko.mvvm.compose)
    commonMainApi(libs.moko.mvvm.flow)
    commonMainApi(libs.moko.mvvm.flow.compose)
}

multiplatformSwiftPackage {
    packageName("theMatchdayQuizIos")
    swiftToolsVersion("5.9")
    targetPlatforms {
        iOS { v("14") }
        macOS { v("12") }
    }
}
