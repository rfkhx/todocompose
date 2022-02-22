package top.ntutn.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import top.ntutn.MyDatabase

class CounterComponent(componentContext: ComponentContext, val onAbout: () -> Unit) :
    ComponentContext by componentContext {
    private val _value = MutableValue(State())
    val state: Value<State> = _value

    init {
        lifecycle.doOnCreate {
            // FIXME 反复创建了Database对象
            // TODO 使用协程
            createDatabase().configQueries
                .readConfig("counter").executeAsOneOrNull()
                ?.toIntOrNull()
                ?.let { savedValue ->
                    _value.reduce { it.copy(count = savedValue) }
                }
        }

        lifecycle.doOnDestroy {
            createDatabase().configQueries
                .saveConfig("counter", _value.value.count.toString())
        }
    }

    fun increment() {
        _value.reduce { it.copy(count = it.count + 1) }
    }

    data class State(val count: Int = 0)
}
