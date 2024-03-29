package top.ntutn.common.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface PortraitRoot {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Todo(val component: TodoComponent): Child()
        class Counter(val component: CounterComponent) : Child()
        class About(val component: AboutComponent) : Child()
    }
}

class PortraitRootComponent(componentContext: ComponentContext) : PortraitRoot, ComponentContext by componentContext {
    private val router =
        router<Config, PortraitRoot.Child>(
            initialConfiguration = Config.Todo,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(config: Config, componentContext: ComponentContext): PortraitRoot.Child =
        when (config) {
            is Config.Counter -> PortraitRoot.Child.Counter(CounterComponent(componentContext) { router.push(Config.About) })
            is Config.About -> PortraitRoot.Child.About(AboutComponent(componentContext))
            is Config.Todo -> PortraitRoot.Child.Todo(TodoComponent(componentContext))
        }

    fun onTodoPage() {
        router.replaceCurrent(Config.Todo)
    }

    fun onAboutPage() {
        router.replaceCurrent(Config.About)
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object Todo: Config()

        @Parcelize
        object Counter : Config()

        @Parcelize
        object About : Config()
    }

    override val routerState: Value<RouterState<*, PortraitRoot.Child>> = router.state
}