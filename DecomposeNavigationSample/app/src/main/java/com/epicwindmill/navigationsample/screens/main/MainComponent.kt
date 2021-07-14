package com.epicwindmill.navigationsample.screens.main

import android.os.Parcelable
import android.util.Log
import com.arkivanov.decompose.*
import com.arkivanov.decompose.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.IScreenA1
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.ScreenA1Component
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.IScreenB1
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.ScreenB1Component
import com.epicwindmill.navigationsample.screens.tabs.third.screenc1.IScreenC1
import com.epicwindmill.navigationsample.screens.tabs.third.screenc1.ScreenC1Component
import kotlinx.parcelize.Parcelize

class MainComponent(
    componentContext: ComponentContext
) : IMain, ComponentContext by componentContext {

    private val router =
        router<Config, IMain.Child>(
            initialConfiguration = Config.ScreenA1,
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
            is Config.ScreenA1 -> IMain.Child.FirstTab(home(componentContext))
            is Config.ScreenB1 -> IMain.Child.SecondTab(leaderboard(componentContext))
            is Config.ScreenC1 -> IMain.Child.ThirdTab(register(componentContext))
        }

    private fun home(componentContext: ComponentContext): IScreenA1 =
        ScreenA1Component(componentContext,
            navigateToA2 = {
                Log.d("MainComponent", "Request to navigate to A2")
            }
        )

    private fun leaderboard(componentContext: ComponentContext): IScreenB1 =
        ScreenB1Component(
            componentContext = componentContext,
            navigateToB2 = {
                Log.d("MainComponent", "Request to navigate to B2")
            }
        )

    private fun register(componentContext: ComponentContext): IScreenC1 =
        ScreenC1Component(
            componentContext = componentContext,
            navigateToC2 = {
                Log.d("MainComponent", "Request to navigate to C2")
            }
        )

    override fun onTabClick(tab: IMain.Tab): Unit =
        when (tab) {
            IMain.Tab.FIRST -> router.replaceCurrent(Config.ScreenA1)
            IMain.Tab.SECOND -> router.replaceCurrent(Config.ScreenB1)
            IMain.Tab.THIRD -> router.replaceCurrent(Config.ScreenC1)
        }

    private fun Config.toTab(): IMain.Tab =
        when (this) {
            is Config.ScreenA1 -> IMain.Tab.FIRST
            is Config.ScreenB1 -> IMain.Tab.SECOND
            is Config.ScreenC1 -> IMain.Tab.THIRD
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object ScreenA1 : Config()

        @Parcelize
        object ScreenB1 : Config()

        @Parcelize
        object ScreenC1 : Config()
    }
}