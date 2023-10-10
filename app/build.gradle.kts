plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.9.0"
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sevenpeaks.zawmyonaing.readease"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sevenpeaks.zawmyonaing.readease"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.activity:activity-compose:1.8.0")

    implementation(platform("androidx.compose:compose-bom:2023.09.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended-android:1.5.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val navVersion = "2.7.4"
    implementation("androidx.navigation:navigation-compose:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

    val hiltVersion = "2.48.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

    val coroutinesVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.github.leonard-palm:compose-state-events:2.0.3")
    implementation("com.github.a914-gowtham:compose-ratingbar:1.3.6")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.09.02"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}