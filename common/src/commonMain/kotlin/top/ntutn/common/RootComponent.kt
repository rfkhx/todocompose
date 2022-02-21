package top.ntutn.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface Root {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Counter(val component: CounterComponent) : Child()
        class About(val component: AboutComponent) : Child()
    }
}

class RootComponent(componentContext: ComponentContext) : Root, ComponentContext by componentContext {
    private val router =
        router<Config, Root.Child>(
            initialConfiguration = Config.Counter,
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(config: Config, componentContext: ComponentContext): Root.Child =
        when (config) {
            is Config.Counter -> Root.Child.Counter(CounterComponent(componentContext) { router.push(Config.About) })
            is Config.About -> Root.Child.About(AboutComponent(componentContext))
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object Counter : Config()

        @Parcelize
        object About : Config()
    }

    override val routerState: Value<RouterState<*, Root.Child>> = router.state
}