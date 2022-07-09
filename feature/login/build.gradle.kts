plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

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

    implementation(project(":core:resources"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
    implementation(project(":core:base"))
    implementation(project(":core:utils"))

    implementation(Deps.androidxCoreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(Deps.lifecycle_runtime)

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)

//    implementation(Deps.navigation_fragment)
    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_ui_ktx)

    implementation(Deps.room_runtime)
    implementation(Deps.room_ktx)
    kapt(Deps.room_compiler)

    implementation(Deps.viewBindingDelegate)
}