package top.ntutn.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import top.ntutn.common.component.CounterComponent

@Composable
fun CounterUi(counterComponent: CounterComponent) {
    val state by counterComponent.state.subscribeAsState()
    val timer by counterComponent.timer.mySubscribeAsState()

    Column {
        Text(text = state.count.toString())
        // FIXME 安卓第一次打开未操作前界面不更新
        Text(text = timer.count.toString())
        println("recompose ${timer}, value = ${timer.count}")

        Button(onClick = counterComponent::increment) {
            Text("Increment")
        }

        Button(onClick = counterComponent.onAbout) {
            Text("About")
        }
    }
}

@Composable
fun <T : Any> Value<T>.mySubscribeAsState(): State<T> {
    val state = remember(this) { mutableStateOf(value) }

    DisposableEffect(this) {
        val observer: ValueObserver<T> = {
            state.value = it
            println("update state($state) value = ${state.value}")
        }

        subscribe(observer)

        onDispose {
            unsubscribe(observer)
        }
    }

    return state
}
