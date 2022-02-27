package top.ntutn.common.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import top.ntutn.common.AboutUI
import top.ntutn.common.component.PortraitRoot
import top.ntutn.common.component.PortraitRootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun PortraitRootContent(rootComponent: PortraitRootComponent) {
    Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is PortraitRoot.Child.Counter -> CounterUi(child.component)
            is PortraitRoot.Child.About -> AboutUI(child.component)
        }
    }
}