package top.ntutn.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.defaultComponentContext
import top.ntutn.common.component.PortraitRootComponent
import top.ntutn.common.ui.PortraitRootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root =
            PortraitRootComponent(
                componentContext = defaultComponentContext(),
            )
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PortraitRootContent(rootComponent = root)
                }
            }
        }
    }
}