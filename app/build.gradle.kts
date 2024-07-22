plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.darya_yushchyk_payback"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.darya_yushchyk_payback"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    api(project(":domain"))

    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycleRuntime)
    implementation(platform(libs.composeBom))
    testImplementation(libs.bundles.unitTests)
    androidTestImplementation(libs.bundles.uiTests)

    androidTestImplementation(platform(libs.composeBom))
    debugImplementation(libs.composeUITooling)
    debugImplementation(libs.composeUITestManifest)

    implementation(libs.bundles.hilt)
    implementation(libs.bundles.okhhtp)
    kapt(libs.hiltCompiler)
    kapt(libs.hiltProcessor)
    implementation(libs.bundles.logging)
    implementation(libs.coil)
    implementation(libs.coil.compose)
}
kapt {
    correctErrorTypes = true
}
