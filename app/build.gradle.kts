plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
//    id("dagger.hilt.android.plugin")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.baseapp_jetpackcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.baseapp_jetpackcompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        kapt {
            correctErrorTypes = true
            useBuildCache = true
        }
    }

    buildTypes {
        release {
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

//    //Hilt and Navigation
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.hilt.navigation.fragment)
////    implementation(libs.androidx.hilt.work)
////    // When using Kotlin.
////    kapt("androidx.hilt:hilt-compiler:1.2.0")
////    kapt("com.google.dagger:hilt-compiler:2.51")
//    // Dagger Core
//    implementation("com.google.dagger:dagger:2.51")
//    kapt("com.google.dagger:dagger-compiler:2.51")
//
//// Dagger Android
//    api("com.google.dagger:dagger-android:2.37")
//    api ("com.google.dagger:dagger-android-support:2.37")
//    kapt("com.google.dagger:dagger-android-processor:2.37")

// Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)


    //Paging 3
    implementation(libs.androidx.paging.runtime)
    // optional - Jetpack Compose integration
    implementation(libs.androidx.paging.compose)

    //JSON
    implementation(libs.kotlinx.serialization.json)

    //Coil - Coil Image Loading Library
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.kt.coil.compose)

    //Splash Screen
    implementation(libs.androidx.core.splashscreen)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Material
    implementation(libs.androidx.material)

    //Material icons
    implementation(libs.androidx.material.icons.extended)

    //  Http inspector
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)
}
apply(plugin = "dagger.hilt.android.plugin")
// Allow references to generated code
kapt {
    correctErrorTypes = true
}