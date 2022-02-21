import java.io.FileInputStream
import java.util.Properties

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.android.tools.build:gradle:4.2.2")
    }
}

group = "top.ntutn"
version = "1.0"

val versionProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "version.properties")))
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