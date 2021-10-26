package com.epicwindmill.decomposekmmnavigationsample.components.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.IScreenA
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.ScreenAComponent
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.IScreenB
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.ScreenBComponent
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.IScreenC
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.ScreenCComponent
import com.epicwindmill.decomposekmmnavigationsample.utils.navigateSingleTop

class MainComponent(
    componentContext: ComponentContext
) : IMain, ComponentContext by componentContext {

    private val router =
        router<Config, IMain.Child>(
            initialConfiguration = Config.ScreenA,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, IMain.Child>> = router.state

    override val model: Value<IMain.Model> =
        router.state.map { state ->
            IMain.Model(
                selectedTab = state.activeChild.configuration.toTab()
            )
        }

    private fun createChild(config: Config, componentContext: ComponentContext): IMain.Child =
        when (config) {
            is Config.ScreenA -> IMain.Child.ScreenA(screenA(componentContext))
            is Config.ScreenB -> IMain.Child.ScreenB(screenB(componentContext))
            is Config.ScreenC -> IMain.Child.ScreenC(screenC(componentContext))
        }

    private fun screenA(componentContext: ComponentContext): IScreenA =
        ScreenAComponent(componentContext)

    private fun screenB(componentContext: ComponentContext): IScreenB =
        ScreenBComponent(componentContext)

    private fun screenC(componentContext: ComponentContext): IScreenC =
        ScreenCComponent(componentContext)

    override fun onTabClick(tab: IMain.Tab): Unit =
        when (tab) {
            IMain.Tab.A -> router.navigateSingleTop(config = {Config.ScreenA})
            IMain.Tab.B -> router.navigateSingleTop(config = {Config.ScreenB})
            IMain.Tab.C -> router.navigateSingleTop(config = {Config.ScreenC})
        }

    private fun Config.toTab(): IMain.Tab =
        when (this) {
            is Config.ScreenA -> IMain.Tab.A
            is Config.ScreenB -> IMain.Tab.B
            is Config.ScreenC -> IMain.Tab.C
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object ScreenA : Config()

        @Parcelize
        object ScreenB : Config()

        @Parcelize
        object ScreenC : Config()
    }
}