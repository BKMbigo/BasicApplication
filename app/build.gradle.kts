plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.github.bkmbigo.basicapplication"
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = "com.github.bkmbigo.basicapplication"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        pickFirst("META-INF/LICENSE.md")
        pickFirst("META-INF/LICENSE-notice.md")
    }
    testOptions {
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Android.Dependencies.androidCoteKtx)
    implementation(Android.Dependencies.appCompat)

    implementation(Android.Dependencies.material)
    implementation(Android.Dependencies.constraintLayout)

    implementation(Android.Dependencies.navigationFragment)
    implementation(Android.Dependencies.navigationUi)

    implementation(Android.Dependencies.lifecycleLiveData)
    implementation(Android.Dependencies.lifecycleViewModel)

    implementation(Android.Dependencies.hiltAndroid)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Android.Dependencies.hiltCompiler)

    implementation(Android.Dependencies.roomRuntime)
    implementation(Android.Dependencies.roomKtx)
    ksp(Android.Dependencies.roomCompiler)

    testImplementation(Android.Dependencies.Testing.jUnit)
    testImplementation(Android.Dependencies.Testing.hiltAndroidTesting)
    testImplementation(Android.Dependencies.Testing.coroutinesTest)
    testImplementation(Android.Dependencies.Testing.mockKAndroid)
    testImplementation(Android.Dependencies.Testing.mockKAgent)
    kaptTest(Android.Dependencies.hiltCompiler)

    androidTestImplementation(Android.Dependencies.Testing.androidJUnit)
    androidTestImplementation(Android.Dependencies.Testing.espressoCore)
    androidTestImplementation(Android.Dependencies.Testing.roomTesting)
    androidTestImplementation(Android.Dependencies.Testing.hiltAndroidTesting)
    androidTestImplementation(Android.Dependencies.Testing.coroutinesTest)
    androidTestImplementation(Android.Dependencies.Testing.mockKAndroid)
    androidTestImplementation(Android.Dependencies.Testing.mockKAgent)
    kaptAndroidTest(Android.Dependencies.hiltCompiler)
}