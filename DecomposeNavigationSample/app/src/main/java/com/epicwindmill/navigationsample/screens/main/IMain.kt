package com.epicwindmill.navigationsample.screens.main

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.IScreenA1
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.IScreenB1
import com.epicwindmill.navigationsample.screens.tabs.third.screenc1.IScreenC1

interface IMain {

    val routerState: Value<RouterState<*, Child>>
    val model: Value<Model>

    fun onTabClick(tab: Tab)

    data class Model(
        val selectedTab: Tab = Tab.FIRST
    )

    enum class Tab {
        FIRST, SECOND, THIRD
    }

    sealed class Child {
        class FirstTab(val component: IScreenA1) : Child()
        class SecondTab(val component: IScreenB1) : Child()
        class ThirdTab(val component: IScreenC1) : Child()
    }
}