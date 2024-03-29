plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        viewBinding.isEnabled = true
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
    namespace = "com.rodionov.meters_data"
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {

    api(project(":core:resources"))
    api(project(":core:database"))
    api(project(":core:base"))
    api(project(":core:utils"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:meter-creator"))
    implementation(project(":feature:login"))

    implementation(Deps.androidxCoreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.constraintLayout)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)

//    implementation(Deps.navigation_fragment)
    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_ui_ktx)

    implementation(Deps.room_runtime)
    implementation(Deps.room_ktx)
    kapt(Deps.room_compiler)

    //ViewBindingDelegate
    implementation(Deps.viewBindingDelegate)
    implementation(Deps.splashScreen)
}