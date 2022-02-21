package top.ntutn.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun CounterUi(counterComponent: CounterComponent) {
    val state by counterComponent.state.subscribeAsState()

    Column {
        Text(text = state.count.toString())

        Button(onClick = counterComponent::increment) {
            Text("Increment")
        }

        Button(onClick = counterComponent.onAbout) {
            Text("About")
        }
    }
}
