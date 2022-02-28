package top.ntutn.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import top.ntutn.common.model.TodoModel
import top.ntutn.common.ui.AboutPage
import top.ntutn.common.ui.TodoListItemUI
import top.ntutn.common.ui.TodoListUI

@Preview
@Composable
fun TodoItemPreview() {
    TodoListItemUI(TodoModel(
        content = "吃饭",
        checked = false
    ))
}

@Preview
@Composable
fun TodoListPreview() {
    TodoListUI(
        listData = listOf(
            TodoModel(
                content = "吃饭",
                checked = true
            ),
            TodoModel(
                content = "睡觉",
                checked = true
            ),
            TodoModel(
                content = "打豆豆",
                checked = false
            ),
        )
    )
}

@Preview
@Composable
fun AboutPreview() {
    AboutPage()
}