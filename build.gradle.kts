// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Android.Plugins.ksp)
        classpath(Android.Plugins.navigationSafeArgs)
    }
}
plugins {
    id("com.android.application") version "7.4.0-beta02" apply false
    id("com.android.library") version "7.4.0-beta02" apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.hiltAndroid apply false
}