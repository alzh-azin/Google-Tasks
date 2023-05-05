import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    //Android UI
     const val coreKtx1 = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
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

    val appLibraries = mutableListOf<String>().apply {
        add(coreKtx1)
        add(lifecycleKtx)
        add(activityCompose)
        add(composeUi)
        add(composeUiPreviewTooling)
        add(materialIconExtended)
        add(material3)
        add(lottie)
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

}