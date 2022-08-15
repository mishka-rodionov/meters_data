object ConfigData {
    const val applicationId = "com.rodionov.meters_data"
    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 31
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val androidxCoreKtx = "1.8.0"
    const val gradlePlugin = "4.2.1"
    const val kotlin = "1.5.0"
    const val timber = "4.7.1"
    const val appCompat = "1.4.2"
    const val material = "1.6.1"
    const val constraintLayout = "2.1.4"
    const val jUnit = "4.12"
    const val dagger = "2.41"
    const val room = "2.4.2"
    const val lifecycle = "2.5.0"
    const val gson = "2.8.6"
    const val converter_gson = "2.6.1"
    const val navigation_fragment = "2.5.0"
    const val nav = "2.5.0"
    const val viewBindingDelegate = "1.5.3"
    const val kotlin_coroutines = "1.3.9"
    const val viewPager2 = "1.0.0"
    const val splashScreen = "1.0.0-beta02"
}

/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {
    val androidxCoreKtx by lazy { "androidx.core:core-ktx:${Versions.androidxCoreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val dagger by lazy { "com.google.dagger:dagger:${Versions.dagger}" }
    val daggerCompiler by lazy { "com.google.dagger:dagger-compiler:${Versions.dagger}" }
    val room_runtime by lazy {"androidx.room:room-runtime:${Versions.room}"}
    val room_compiler by lazy {"androidx.room:room-compiler:${Versions.room}"}
    val room_ktx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val lifecycle_extension by lazy { "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}" }
    val lifecycle_viewmodel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val lifecycle_runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val converter_gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}" }
    val navigation_fragment by lazy { "android.arch.navigation:navigation-fragment:${Versions.navigation_fragment}" }
    val navigation_fragment_ktx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.nav}" }
    val navigation_ui_ktx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.nav}" }
    val viewBindingDelegate by lazy { "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingDelegate}" }
    val coroutines by lazy {"org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"}
    val viewpager2 by lazy {"androidx.viewpager2:viewpager2:${Versions.viewPager2}"}
    val splashScreen by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen}" }
}