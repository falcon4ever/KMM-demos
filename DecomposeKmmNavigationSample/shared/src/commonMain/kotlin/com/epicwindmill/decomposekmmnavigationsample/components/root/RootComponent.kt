package com.epicwindmill.decomposekmmnavigationsample.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.epicwindmill.decomposekmmnavigationsample.components.main.IMain
import com.epicwindmill.decomposekmmnavigationsample.components.main.MainComponent

class RootComponent(
    componentContext: ComponentContext
) : IRoot, ComponentContext by componentContext {

    private val router =
        router<Config, IRoot.Child>(
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, IRoot.Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): IRoot.Child =
        when (config) {
            is Config.Main -> IRoot.Child.Main(main(componentContext))
        }

    private fun main(componentContext: ComponentContext): IMain =
        MainComponent(componentContext)

    private sealed class Config : Parcelable {
        @Parcelize
        object Main : Config()
    }
}