package top.ntutn.common.component

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import top.ntutn.common.AboutUI
import top.ntutn.common.ui.CounterUi

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(rootComponent: RootComponent) {
    Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is Root.Child.Counter -> CounterUi(child.component)
            is Root.Child.About -> AboutUI(child.component)
        }
    }
}