plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.example.nycschools"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nycschools"
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    // Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    //hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    // Retrofit for network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Coroutines for asynchronous tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1") // Coroutines Core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // For StateFlow + Compose integration
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.2")
    implementation ("androidx.compose.runtime:runtime:1.6.2")

    // Paging
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    // For integration with Compose
    implementation ("androidx.paging:paging-compose:3.2.1")

    //Test
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Mockito
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.mockito:mockito-inline:3.12.4")
    testImplementation("org.mockito:mockito-android:3.12.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.assertj:assertj-core:3.20.2")

    // MockWebServer for network testing
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")

    // Icon
    implementation("androidx.compose.material:material-icons-core:1.6.2")
    implementation("androidx.compose.material:material-icons-extended:1.6.2")
}