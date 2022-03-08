import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "top.ntutn"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            modules("java.instrument", "java.sql", "jdk.unsupported")
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "海瑞待办"
            packageVersion = "1.0.0"

            linux {
                packageName = "hairuitodo"
                debMaintainer = "ntutn.top@gmail.com"
                iconFile.set(project.file("todo_launcher.png"))
            }

            macOS {
                packageName = "hairuitodo"
                dockName = "海瑞待办"
                bundleID = "top.ntutn.hrtodo"
                iconFile.set(project.file("todo_launcher.icns"))
                jvmArgs += listOf("-Xdock:icon=src/jvmMain/resources/todo_launcher.png")
            }

            windows {
                iconFile.set(project.file("todo_launcher.ico"))
            }
        }
    }
}