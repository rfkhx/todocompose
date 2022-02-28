package top.ntutn.common.util

import androidx.compose.runtime.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver

class ValueWrapper<T>(val wrapped: T) : Cloneable {
    override fun clone(): Any {
        return ValueWrapper(wrapped)
    }
}

/**
 * State只有在Value发生改变时才会通知观察者，但有时我们仅仅只是更新对象的部分属性，或增删list的元素。
 * 如果按照原始的写法，我们只好定义 MutableValue<ValueWrapper<List<String>>>
 * 这个方法起到自动创建ValueWrapper的作用
 */
@Composable
fun <T : Any> Value<T>.subscribeAsWrappedState(): State<ValueWrapper<T>> {
    val state = remember(this) { mutableStateOf(ValueWrapper(value)) }

    DisposableEffect(this) {
        val observer: ValueObserver<T> = { state.value = ValueWrapper(it) }

        subscribe(observer)

        onDispose {
            unsubscribe(observer)
        }
    }

    return state
}
