val compose_ui_version: String by project
val koin_version: String by project

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.kursor.crypto"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kursor.crypto"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":model"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation("io.coil-kt:coil-compose:1.4.0")

    //Koin di
    // Koin Core features
    implementation("io.insert-koin:koin-core:$koin_version")
    // Koin main features for Android
    implementation("io.insert-koin:koin-android:$koin_version")
    // Navigation Graph
    implementation("io.insert-koin:koin-androidx-navigation:$koin_version")
    // Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:$koin_version")

    val nav_version = "2.5.1"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.compose.ui:ui:$compose_ui_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.compose.runtime:runtime-livedata:$compose_ui_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")
}