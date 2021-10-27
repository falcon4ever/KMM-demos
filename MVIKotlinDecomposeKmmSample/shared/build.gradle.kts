import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
                transitiveExport = true
                export("com.arkivanov.decompose:decompose:${rootProject.extra["decomposeVersion"]}")
                export("com.arkivanov.mvikotlin:mvikotlin-main:${rootProject.extra["mviKotlinVersion"]}")
            }
        }
    }
    


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.arkivanov.decompose:decompose:${rootProject.extra["decomposeVersion"]}")

                implementation("com.arkivanov.mvikotlin:mvikotlin:${rootProject.extra["mviKotlinVersion"]}")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${rootProject.extra["mviKotlinVersion"]}")
                implementation("com.arkivanov.mvikotlin:rx:${rootProject.extra["mviKotlinVersion"]}")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.extra["coroutinesVersion"]}")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${rootProject.extra["serializationVersion"]}")

                implementation("io.ktor:ktor-client-core:${rootProject.extra["ktorVersion"]}")
                implementation("io.ktor:ktor-client-serialization:${rootProject.extra["ktorVersion"]}")
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
                implementation("io.ktor:ktor-client-android:${rootProject.extra["ktorVersion"]}")
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
                implementation("io.ktor:ktor-client-ios:${rootProject.extra["ktorVersion"]}")
                api("com.arkivanov.decompose:decompose:${rootProject.extra["decomposeVersion"]}")
                api("com.arkivanov.mvikotlin:mvikotlin-main:${rootProject.extra["mviKotlinVersion"]}")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 30
    }
}