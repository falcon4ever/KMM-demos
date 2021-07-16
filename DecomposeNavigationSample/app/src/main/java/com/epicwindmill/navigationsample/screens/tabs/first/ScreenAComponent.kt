package com.epicwindmill.navigationsample.screens.tabs.first

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.value.Value
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.IScreenA1
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.ScreenA1Component
import com.epicwindmill.navigationsample.screens.tabs.first.screena2.IScreenA2
import com.epicwindmill.navigationsample.screens.tabs.first.screena2.ScreenA2Component
import kotlinx.parcelize.Parcelize

class ScreenAComponent(
    componentContext: ComponentContext
) : IScreenA, ComponentContext by componentContext {

    private val router =
        router<Config, IScreenA.Child>(
            initialConfiguration = Config.ScreenA1,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, IScreenA.Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): IScreenA.Child =
        when (config) {
            is Config.ScreenA1 -> IScreenA.Child.ScreenA1(screenA1(componentContext))
            is Config.ScreenA2 -> IScreenA.Child.ScreenA2(screenA2(componentContext))
        }

    private fun screenA1(componentContext: ComponentContext): IScreenA1 =
        ScreenA1Component(componentContext) {
            router.push(Config.ScreenA2)
        }

    private fun screenA2(componentContext: ComponentContext): IScreenA2 =
        ScreenA2Component(componentContext)

    private sealed class Config : Parcelable {
        @Parcelize
        object ScreenA1 : Config()

        @Parcelize
        object ScreenA2 : Config()
    }
}