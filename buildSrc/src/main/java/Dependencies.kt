import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.wordssearcher"
    const val compile_sdk = 31
    const val min_sdk = 23
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
}

object Versions {
    //Design
    const val appcompat = "1.4.0"
    const val material = "1.4.0"
    const val constraintlayout = "2.1.2"
    const val legacysupportv4 = "1.0.0"

    //Kotlin
    const val core = "1.7.0"
    const val stdlib = "1.6.0"
    const val coroutinesCore = "1.5.1"
    const val coroutinesAndroid = "1.5.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinAndroid = "3.1.2"
    const val koinViewModel = "3.1.2"

    //Coil
    const val coil = "1.3.2"
    const val coilgif = "1.3.2"

    //Room
    const val roomKtx = "2.3.0"
    const val runtime = "2.3.0"
    const val roomCompiler = "2.3.0"

    //Test
    const val jUnit = "4.+"
    const val androidxJunit = "1.1.3"
    const val espressoCore = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinAndroid}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koinAndroid}"
    //const val koin_view_model = "io.insert-koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coil_gif = "io.coil-kt:coil-gif:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val extjunit = "androidx.test:ext:junit:${Versions.androidxJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}