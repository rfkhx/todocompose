import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import top.ntutn.common.component.LandscapeRootComponent
import top.ntutn.common.ui.LandscapeRootContent
import top.ntutn.common.ui.theme.TodoTheme

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val root = // Counter()
        LandscapeRootComponent(
            componentContext = DefaultComponentContext(lifecycle),
        )
    application {
        val windowState = rememberWindowState()
        val windowIcon = painterResource("todo_launcher.png")
        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = {
                exitApplication()
            },
            state = windowState,
            title = "海瑞待办",
            icon = windowIcon
        ) {
            TodoTheme {
                LandscapeRootContent(root)
            }
        }
    }
}