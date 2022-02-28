package top.ntutn.android

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.arkivanov.decompose.defaultComponentContext
import top.ntutn.common.component.LandscapeRootComponent
import top.ntutn.common.component.PortraitRootComponent
import top.ntutn.common.ui.LandscapeRootContent
import top.ntutn.common.ui.PortraitRootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "海瑞待办"
        val useLandscapeLayout = isLandscape() && isTabletDevice()

        if (useLandscapeLayout) {
            val root = LandscapeRootComponent(
                componentContext = defaultComponentContext()
            )
            setContent {
                MaterialTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        LandscapeRootContent(rootComponent = root)
                    }
                }
            }
        } else {
            val root = PortraitRootComponent(
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

    private fun isTabletDevice(): Boolean {
        return resources.configuration.screenLayout.and(Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    private fun isLandscape(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}