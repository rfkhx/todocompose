import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.0.0"
    id("com.android.library")
    id("kotlin-parcelize")
    id("com.arkivanov.gradle.setup")
    id("com.squareup.sqldelight")
}

val appVersionCode: String by ext
val appVersionName: String by ext

group = "top.ntutn"
version = appVersionName

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            kotlin.setSrcDirs(listOf("build/generated/source/appInfo", "src/commonMain/kotlin"))
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api("com.arkivanov.decompose:decompose:0.5.1")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:0.5.1")
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.4.1")
                api("androidx.core:core-ktx:1.7.0")
                implementation ("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation ("com.squareup.sqldelight:sqlite-driver:1.5.3")
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        buildToolsVersion = "31.0.0"
//        versionCode = appVersionCode.toInt()
//        versionName = appVersionName
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    database("MyDatabase") { // This will be the name of the generated database class.
        packageName = "top.ntutn"
    }
}

val generateAppInfoFile =tasks.register("generateAppInfoFile") {
    val content = """
        // Do not modify this file. It's generated by build.gradle.kts in common module
        object AppInfo {
            const val VERSION_CODE = $appVersionCode
            const val VERSION_NAME = "$appVersionName"
        }
    """.trimIndent()
    val appInfoDir = File(project.buildDir,"generated/source/appInfo")
    appInfoDir.mkdirs()
    val appInfoFile = File(appInfoDir, "AppInfo.kt")
    appInfoFile.writeText(content)
}

tasks.matching {
    it.name in listOf("preBuild", "syncDebugLibJars")
}.all {
    dependsOn(generateAppInfoFile)
}