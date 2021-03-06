package com.epicwindmill.mvikotlindecomposekmmsample.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router


import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.epicwindmill.mvikotlindecomposekmmsample.api.SwansonQuotes
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.IMain
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.MainComponent

class RootComponent internal constructor(
    componentContext: ComponentContext,
    private val main: (ComponentContext) -> IMain,
) : IRoot, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        quotesApi: SwansonQuotes
    ) : this(
        componentContext = componentContext,
        main = { childContext ->
            MainComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                quotesApi = quotesApi
            )
        }
    )

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

    private sealed class Config : Parcelable {
        @Parcelize
        object Main : Config()
    }
}