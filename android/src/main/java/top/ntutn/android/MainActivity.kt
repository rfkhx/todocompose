package top.ntutn.android

import top.ntutn.common.App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import top.ntutn.common.RootComponent
import top.ntutn.common.RootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root =
            RootComponent(
                componentContext = defaultComponentContext(),
            )
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RootContent(rootComponent = root)
                }
            }
        }
    }
}