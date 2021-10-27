buildscript {

    val kotlinVersion by rootProject.extra { "1.5.31" }
    val decomposeVersion by rootProject.extra { "0.4.0" }
    val composeVersion by rootProject.extra { "1.0.4" }
    val mviKotlinVersion by rootProject.extra { "3.0.0-alpha02" }
    val essentyVersion by rootProject.extra { "0.2.2" }

    val serializationVersion by rootProject.extra { "1.3.0" }
    val ktorVersion by rootProject.extra { "1.6.4" }
    val coroutinesVersion by rootProject.extra { "1.5.2-native-mt" }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    // https://github.com/icerockdev/moko-mvvm/issues/125#issuecomment-815414538 Odd workaround but it works
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}