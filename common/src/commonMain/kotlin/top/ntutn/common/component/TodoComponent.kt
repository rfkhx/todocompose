package top.ntutn.common.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import top.ntutn.common.datasource.createDatabase
import top.ntutn.common.model.TodoModel
import top.ntutn.common.model.TodoRecordModel
import top.ntutn.common.util.LifecycleScopeHelper
import top.ntutn.common.util.LifecycleScopeHolder

class TodoComponent(componentContext: ComponentContext) : ComponentContext by componentContext,
    LifecycleScopeHolder by LifecycleScopeHelper(componentContext.lifecycle) {
    private val _tasks = MutableValue(listOf<TodoModel>())

    val tasks: Value<List<TodoModel>> get() = _tasks

    init {
        lifecycle.doOnCreate {
            lifecycleScope.launch {
                val dataList = withContext(Dispatchers.IO) {
                    createDatabase().todoTBQueries.selectAll().executeAsList().map {
                        TodoModel.fromTable(it)
                    }
                }
                _tasks.value = dataList
            }
        }
    }

    fun addTask(task: String) {
        val todoModel = TodoModel(
            content = task
        )
        _tasks.value = _tasks.value.toMutableList().also { it.add(todoModel) }
        GlobalScope.launch {
            val database = createDatabase()
            database.transaction {
                database.todoTBQueries.addItem(
                    id = todoModel.id,
                    content = todoModel.content,
                    create_time = todoModel.createTime,
                    modifiy_time = todoModel.modifyTime
                )
                database.todoRecordTBQueries.addRecord(
                    iid = todoModel.id,
                    operation_time = todoModel.createTime,
                    operation = TodoRecordModel.OPERATION_CREATE
                )
            }
        }
    }

    fun checkItem(id: String) {
        val newList = _tasks.value
        val operationItem = newList.find { it.id == id } ?: return
        operationItem.checked = !(operationItem.checked)
        _tasks.value = newList
        GlobalScope.launch {
            val database = createDatabase()
            database.transaction {
                val modifyTime = System.currentTimeMillis()
                database.todoTBQueries.checkItem(
                    checked = operationItem.checked,
                    modifiy_time = modifyTime,
                    id = operationItem.id
                )
                database.todoRecordTBQueries.addRecord(
                    iid = operationItem.id,
                    operation_time = modifyTime,
                    operation = TodoRecordModel.OPERATION_MODIFY
                )
            }
        }
    }
}