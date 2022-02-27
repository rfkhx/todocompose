package top.ntutn.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import top.ntutn.common.AboutUI
import top.ntutn.common.component.LandscapeRoot
import top.ntutn.common.component.LandscapeRootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun LandscapeRootContent(rootComponent: LandscapeRootComponent) {
    Row {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = rootComponent::onTodoPage) {
                Text("TODO")
            }
            Button(onClick = rootComponent::onAboutPage) {
                Text("关于")
            }
        }
        Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
            when (val child = it.instance) {
                is LandscapeRoot.Child.Todo -> TodoUI(child.component)
                is LandscapeRoot.Child.Counter -> CounterUi(child.component)
                is LandscapeRoot.Child.About -> AboutUI(child.component)
                else -> throw NotImplementedError("未绑定UI")
            }
        }
    }
}