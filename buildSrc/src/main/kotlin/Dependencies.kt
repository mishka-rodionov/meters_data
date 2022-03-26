object ConfigData {
    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 31
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val gradlePlugin = "4.2.1"
    const val kotlin = "1.5.0"
    const val timber = "4.7.1"
    const val appCompat = "1.3.0"
    const val material = "1.3.0"
    const val constraintLayout = "1.1.3"
    const val jUnit = "4.12"
    const val dagger = "2.41"
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
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val dagger by lazy { "com.google.dagger:dagger:${Versions.dagger}" }
    val daggerCompiler by lazy { "com.google.dagger:dagger-compiler:${Versions.dagger}" }

}