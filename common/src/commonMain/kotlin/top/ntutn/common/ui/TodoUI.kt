package top.ntutn.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import top.ntutn.common.component.TodoComponent
import top.ntutn.common.model.TodoModel
import top.ntutn.common.util.subscribeAsWrappedState

@Composable
fun TodoUI(todoComponent: TodoComponent) {
    val tasks by todoComponent.tasks.subscribeAsWrappedState()

    Column(modifier = Modifier.fillMaxHeight()) {
        TodoListUI(tasks.wrapped, modifier = Modifier.weight(1f).fillMaxWidth(),
            onCheckItem = {
                todoComponent.checkItem(it)
            })
        Surface(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            TodoInputArea(modifier = Modifier.fillMaxWidth()) {
                todoComponent.addTask(it)
            }
        }
    }
}

@Composable
fun TodoListItemUI(itemData: TodoModel, modifier: Modifier = Modifier, onCheckedChange: ((Boolean) -> Unit)? = null) {
    Row(modifier = modifier.padding(16.dp, 8.dp, 16.dp, 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = itemData.checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(itemData.content)
    }
}

@Composable
fun TodoListUI(listData: List<TodoModel>, modifier: Modifier = Modifier, onCheckItem: ((String) -> Unit)? = null) {
    LazyColumn(modifier = modifier) {
        items(listData.size) { i ->
            TodoListItemUI(listData[i], onCheckedChange = {
                onCheckItem?.invoke(listData[i].id)
            })
        }
    }
}

@Composable
fun TodoInputArea(modifier: Modifier = Modifier, onCreateItem: (String) -> Unit) {
    Row(modifier = modifier.padding(16.dp, 8.dp, 16.dp, 8.dp)) {
        var inputtingText by remember { mutableStateOf("") }
        val sendAction = sendAction@{
            if (inputtingText.isBlank()) {
                return@sendAction
            }
            onCreateItem(inputtingText.trim())
            inputtingText = ""
        }
        OutlinedTextField(
            value = inputtingText,
            onValueChange = {
                inputtingText = it
                if (inputtingText.endsWith("\n")) {
                    sendAction()
                }
            },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    sendAction()
                }
            ),
        )
        Spacer(Modifier.width(8.dp))
        Button(onClick = sendAction) {
            Text("添加")
        }
    }
}