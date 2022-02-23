import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import top.ntutn.common.component.RootComponent
import top.ntutn.common.component.RootContent

fun main() {
    val lifecycle = LifecycleRegistry()
    val root = // Counter()
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle),
        )
    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = {
                exitApplication()
            },
            state = windowState,
            title = "Decompose Dynamic Features"
        ) {
            MaterialTheme {
                RootContent(root)
            }
        }
    }
}