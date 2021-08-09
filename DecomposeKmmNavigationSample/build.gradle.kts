buildscript {

    val kotlinVersion = "1.5.10" // For Android because of Compose 1.0.0 being tied to this version...
    // val kotlinVersion = "1.5.21" // For iOS and future compose versions
    val decomposeVersion by rootProject.extra { "0.3.1" }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:7.0.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}