package top.ntutn.common.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface LandscapeRoot {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Todo(val component: TodoComponent): Child()
        class Counter(val component: CounterComponent) : Child()
        class About(val component: AboutComponent) : Child()
    }
}

class LandscapeRootComponent(componentContext: ComponentContext) : LandscapeRoot, ComponentContext by componentContext {
    private val router =
        router<Config, LandscapeRoot.Child>(
            initialConfiguration = Config.Todo,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(config: Config, componentContext: ComponentContext): LandscapeRoot.Child =
        when (config) {
            is Config.Todo -> LandscapeRoot.Child.Todo(TodoComponent(componentContext))
            is Config.Counter -> LandscapeRoot.Child.Counter(CounterComponent(componentContext) { router.push(Config.About) })
            is Config.About -> LandscapeRoot.Child.About(AboutComponent(componentContext))
        }

    fun onTodoPage() {
        router.replaceCurrent(Config.Todo)
    }

    fun onAboutPage() {
        router.replaceCurrent(Config.About)
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object Todo : Config()

        @Parcelize
        object Counter : Config()

        @Parcelize
        object About : Config()
    }

    override val routerState: Value<RouterState<*, LandscapeRoot.Child>> = router.state
}