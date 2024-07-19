object Versions {
    const val KOTLIN_VERSION = "1.9.25"
    const val AGP_VERSION = "7.1.2"
    const val COMPOSE_VERSION = "1.1.0"

    const val SQL_DELIGHT = "1.5.3"
    const val DECOMPOSE_GRADLE_PLUGIN = "9f046bd98f"
    const val DECOMPOSE = "0.5.1"

    const val DEPENDENCY_UPDATE = "0.42.0"

    const val ANDROIDX_ACTIVITY = "1.4.0"
    const val ANDROIDX_APPCOMPAT = "1.4.1"
    const val ANDROIDX_CORE = "1.7.0"

    const val AUTO_SERVICE = "1.0.1"
    const val JUNIT = "4.13.2"
}

object Deps {
    const val SQL_DELIGHT_ID = "com.squareup.sqldelight"
    const val sqlDelightGradlePlugin = "$SQL_DELIGHT_ID:gradle-plugin:${Versions.SQL_DELIGHT}"
    const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:${Versions.SQL_DELIGHT}"
    const val sqlDelightDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.SQL_DELIGHT}"

    const val DECOMPOSE_GRADLE_SETUP_ID = "com.arkivanov.gradle.setup"
    const val decomposeGradlePlugin = "com.github.arkivanov:gradle-setup-plugin:${Versions.DECOMPOSE_GRADLE_PLUGIN}"
    const val decompose = "com.arkivanov.decompose:decompose:${Versions.DECOMPOSE}"
    const val decomposeJetBrainsExtension = "com.arkivanov.decompose:extensions-compose-jetbrains:${Versions.DECOMPOSE}"

    const val androidXActivityCompose = "androidx.activity:activity-compose:${Versions.ANDROIDX_ACTIVITY}"
    const val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val androidXCore = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE}"

    const val autoService = "com.google.auto.service:auto-service:${Versions.AUTO_SERVICE}"
    const val junit = "junit:junit:${Versions.JUNIT}"
}