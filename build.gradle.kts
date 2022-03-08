import java.io.FileInputStream
import java.util.Properties
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("jvm") version Versions.KOTLIN_VERSION apply false
    kotlin("multiplatform") version Versions.KOTLIN_VERSION apply false
    kotlin("android") version Versions.KOTLIN_VERSION apply false
    id("com.android.application") version Versions.AGP_VERSION apply false
    id("com.android.library") version Versions.AGP_VERSION apply false
    id("org.jetbrains.compose") version Versions.COMPOSE_VERSION apply false
    id("com.github.ben-manes.versions") version Versions.DEPENDENCY_UPDATE
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath(Deps.sqlDelightGradlePlugin)
    }
}

group = "top.ntutn"
version = "1.0"

val versionProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "version.properties")))
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}


// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    ext {
        set("appVersionCode", versionProperties.getProperty("appversion_version_code"))
        set("appVersionName", versionProperties.getProperty("appversion_version_name"))
    }
}