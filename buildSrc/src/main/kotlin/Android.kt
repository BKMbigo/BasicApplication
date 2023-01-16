object Android {
    const val minSdk = 24
    const val targetSdk = 33
    const val compileSdk = targetSdk

    const val applicationId = "com.github.bkmbigo.basicapplication"
    const val versionCode = 1
    const val versionName = "1.0.0-SNAPSHOT.01"

    object Plugins {
        const val ksp =
            "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.ksp}"
    }

    object Dependencies {
        const val androidCoteKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltAndroid}"

        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

        object Testing {
            const val jUnit = "junit:junit:${Versions.jUnit}"
            const val androidJUnit = "androidx.test.ext:junit-ktx:${Versions.androidJUnit}"
            const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            const val hiltAndroidTesting =
                "com.google.dagger:hilt-android-testing:${Versions.hiltAndroid}"
            const val roomTesting = "androidx.room:room-testing:${Versions.room}"
            const val coroutinesTest =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
            const val mockKAndroid = "io.mockk:mockk-android:${Versions.mockK}"
            const val mockKAgent = "io.mockk:mockk-agent:${Versions.mockK}"
        }
    }
}