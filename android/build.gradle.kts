plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "top.ntutn"
version = "1.0"

repositories {
}

dependencies {
    implementation(project(":common"))
    implementation(Deps.androidXActivityCompose)
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "top.ntutn.hrtodo.android"
        buildToolsVersion = "31.0.0"
        minSdk = 24
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            resValue("string", "app_name", "hrtodo")
            isMinifyEnabled = false
        }
        getByName("release") {
            resValue("string", "app_name", "海瑞待办")
            isMinifyEnabled = true
        }
    }
}