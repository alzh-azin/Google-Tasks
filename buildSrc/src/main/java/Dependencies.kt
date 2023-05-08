import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    //Android UI
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val lifecycleKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    private const val activityCompose =
        "androidx.activity:activity-compose:${Versions.activityCompose}"
    private const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composeUiPreviewTooling =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    private const val materialIconExtended =
        "androidx.compose.material:material-icons-extended:${Versions.materialIconExtended}"
    private const val material3 = "androidx.compose.material3:material3:${Versions.material}"

    //Lottie
    private const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"

    //Test libs
    private const val junit = "junit:junit:${Versions.junit}"

    //Android Test libs
    private const val uiTextJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    //Debug
    private const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    //OAuth
    private const val playServiceAuth =
        "com.google.android.gms:play-services-auth:${Versions.playServiceAuth}"
    private const val googleApiServicesDocs =
        "com.google.apis:google-api-services-docs:${Versions.googleApiServicesDocs}"
    private const val jwtDecode = "com.auth0.android:jwtdecode:${Versions.jwtDecode}"
    private const val appAuth = "net.openid:appauth:${Versions.appAuth}"

    //Coroutine
    private const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    private const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    //Hilt
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    private const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
    private const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    private const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    //Okhttp
    private const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    //Retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    //Navigation
    private const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    //ViewModel
    private const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModel}"
    private const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.viewModel}"


    val appLibraries = mutableListOf<String>().apply {
        add(coreKtx)
        add(lifecycleKtx)
        add(activityCompose)
        add(composeUi)
        add(composeUiPreviewTooling)
        add(materialIconExtended)
        add(material3)
        add(lottie)
        add(playServiceAuth)
        add(googleApiServicesDocs)
        add(jwtDecode)
        add(appAuth)
        add(coroutineCore)
        add(coroutineAndroid)
        add(hiltAndroid)
        add(okhttp)
        add(navigation)
        add(lifecycleViewModelCompose)
        add(hiltNavigationCompose)
    }

    val testLibraries = mutableListOf<String>().apply {
        add(junit)
    }

    val androidTestLibraries = mutableListOf<String>().apply {
        add(uiTextJunit)
    }

    val debugLibraries = mutableListOf<String>().apply {
        add(uiTooling)
        add(uiTestManifest)
    }

    val kaptLibraries = mutableListOf<String>().apply {
        add(daggerHiltCompiler)
        add(hiltCompiler)
    }

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

    fun DependencyHandler.debugImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("debugImplementation", dependency)
        }
    }

    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

}