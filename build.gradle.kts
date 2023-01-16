// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Android.Plugins.ksp)
    }
}
plugins {
    id("com.android.application") version "8.0.0-alpha11" apply false
    id("com.android.library") version "8.0.0-alpha11" apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.hiltAndroid apply false
}