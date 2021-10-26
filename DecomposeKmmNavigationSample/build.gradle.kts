buildscript {

    val kotlinVersion by rootProject.extra { "1.5.31" }
    val decomposeVersion by rootProject.extra { "0.4.0" }
    val composeVersion by rootProject.extra { "1.0.4" }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:7.0.3")
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