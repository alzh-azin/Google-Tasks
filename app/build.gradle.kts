import Dependencies.androidTestImplementation
import Dependencies.debugImplementation
import Dependencies.implementation
import Dependencies.kapt
import Dependencies.testImplementation

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("com.google.protobuf").version(Versions.protoBuf)
}

android {
    namespace = "com.example.tasks"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.example.tasks"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }

        manifestPlaceholders["appAuthRedirectScheme"] = "com.example.tasks"

        packaging {
            resources {
                excludes += "META-INF/DEPENDENCIES"
            }
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.appLibraries)
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
    debugImplementation(Dependencies.debugLibraries)
    kapt(Dependencies.kaptLibraries)

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${Versions.protobufProtoc}"

    }

    generateProtoTasks {
        all().forEach {
            it.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}