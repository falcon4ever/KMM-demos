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
            }
        }
    }
    
    val serializationVersion = "1.2.2"
    val ktorVersion = "1.6.1"
    val coroutinesVersion = "1.5.1-native-mt"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.arkivanov.decompose:decompose:${rootProject.extra["decomposeVersion"]}")

                implementation("com.arkivanov.mvikotlin:mvikotlin:${rootProject.extra["mviKotlinVersion"]}")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${rootProject.extra["mviKotlinVersion"]}")
                implementation("com.arkivanov.mvikotlin:rx:${rootProject.extra["mviKotlinVersion"]}")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

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

                api("com.arkivanov.decompose:decompose:${rootProject.extra["decomposeVersion"]}")
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