package top.ntutn.common.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.doOnStart
import com.arkivanov.essenty.lifecycle.doOnStop
import kotlinx.coroutines.*
import top.ntutn.common.datasource.createDatabase
import top.ntutn.common.util.LifecycleScopeHelper
import top.ntutn.common.util.LifecycleScopeHolder

@OptIn(DelicateCoroutinesApi::class)
class CounterComponent(componentContext: ComponentContext, val onAbout: () -> Unit) :
    ComponentContext by componentContext,
    LifecycleScopeHolder by LifecycleScopeHelper(componentContext.lifecycle) {
    private val _value = MutableValue(State())
    private val _timer = MutableValue(State())
    val state: Value<State> = _value
    val timer: Value<State> = _timer

    init {
        lifecycle.doOnStart {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    createDatabase().configQueries
                        .readConfig("counter").executeAsOneOrNull()
                        ?.toIntOrNull()
                }?.let { savedValue ->
                    _value.reduce { it.copy(count = savedValue) }
                }
            }
            lifecycleScope.launch {
                while (true) {
                    delay(100)
                    _timer.reduce { it.copy(it.count + 1) }
                }
            }
        }

        lifecycle.doOnStop {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    createDatabase().configQueries
                        .saveConfig("counter", _value.value.count.toString())
                }
            }
        }
    }

    fun increment() {
        _value.reduce { it.copy(count = it.count + 1) }
    }

    data class State(val count: Int = 0)
}
