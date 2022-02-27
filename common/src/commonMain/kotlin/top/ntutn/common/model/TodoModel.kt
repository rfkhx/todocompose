package top.ntutn.common.model

import top.ntutn.sqldelight.todo.data.TodoTB
import java.util.UUID

/**
 * 一个普通的TODO item
 */
data class TodoModel(
    var id: String = UUID.randomUUID().toString(),
    var content: String = "",
    var createTime: Long = System.currentTimeMillis(),
    var modifyTime: Long = System.currentTimeMillis(),
    var checked: Boolean = false,
) {
    companion object {
        fun fromTable(todoTB: TodoTB): TodoModel {
            return TodoModel(
                id = todoTB.id,
                content = todoTB.content,
                createTime = todoTB.create_time,
                modifyTime = todoTB.modifiy_time,
                checked = todoTB.checked,
            )
        }
    }
}