buildscript {

    val kotlinVersion by rootProject.extra { "1.5.21" }
    val decomposeVersion by rootProject.extra { "0.3.1" }
    val composeVersion by rootProject.extra { "1.0.1" }
    val mviKotlinVersion by rootProject.extra { "2.0.4" }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
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