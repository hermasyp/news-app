plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.catnip.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.catnip.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        buildConfig = true
    }
    flavorDimensions += "environment"
    productFlavors {
        create("mockEnv") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"7b0b606b13304c71b64ff4c930237004\"")
        }
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.unittest)
    implementation(libs.coil)
    implementation(libs.koin.android)
    implementation(libs.androidx.startup)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)
}