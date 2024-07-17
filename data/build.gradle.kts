plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.devtools)
    alias(libs.plugins.compose.compiler)

    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}


android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.bundles.android)

    implementation(libs.bundles.room)
//    implementation(libs.room)
    implementation(libs.coreKtx)
    implementation(libs.appcompat)

    implementation(platform(libs.okhhtpBom))
    implementation(libs.bundles.okhhtp)
    implementation(libs.com.google.android.material.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidJunit)
    androidTestImplementation(libs.espresso)
    implementation(libs.storage)
    implementation(libs.bundles.logging)
    implementation(libs.bundles.hilt)
    ksp(libs.hiltCompiler)
    ksp(libs.moshiCodegen)
    implementation(libs.coil)
    ksp(libs.roomKsp)
}