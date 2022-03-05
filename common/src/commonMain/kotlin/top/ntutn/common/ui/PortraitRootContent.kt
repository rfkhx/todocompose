package top.ntutn.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import top.ntutn.common.AboutUI
import top.ntutn.common.component.PortraitRoot
import top.ntutn.common.component.PortraitRootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun PortraitRootContent(rootComponent: PortraitRootComponent) {
    Column {
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
                when (val child = it.instance) {
                    is PortraitRoot.Child.Counter -> CounterUi(child.component)
                    is PortraitRoot.Child.About -> AboutUI(child.component)
                    is PortraitRoot.Child.Todo -> TodoUI(child.component)
                    else -> throw NotImplementedError("未绑定UI")
                }
            }
        }
        Surface(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            Row(modifier = Modifier.padding(16.dp)) {
                Button(onClick = rootComponent::onTodoPage) {
                    Text("TODO")
                }
                Button(onClick = rootComponent::onAboutPage) {
                    Text("关于")
                }
            }
        }
    }
}