import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    val serializationVersion = "1.2.2"
    val ktorVersion = "1.6.1"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.arkivanov.decompose:decompose:0.2.6")

                implementation("com.arkivanov.mvikotlin:mvikotlin:2.0.4")
                implementation("com.arkivanov.mvikotlin:mvikotlin-main:2.0.4")
                implementation("com.arkivanov.mvikotlin:mvikotlin-logging:2.0.4")
                implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:2.0.4")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:2.0.4")

                implementation("com.arkivanov.mvikotlin:rx:2.0.4")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}