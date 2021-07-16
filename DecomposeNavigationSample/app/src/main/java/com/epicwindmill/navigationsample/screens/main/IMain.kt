package com.epicwindmill.navigationsample.screens.main

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.navigationsample.screens.tabs.first.IScreenA
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.IScreenA1
import com.epicwindmill.navigationsample.screens.tabs.second.IScreenB
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.IScreenB1
import com.epicwindmill.navigationsample.screens.tabs.third.IScreenC
import com.epicwindmill.navigationsample.screens.tabs.third.screenc1.IScreenC1

interface IMain {

    val routerState: Value<RouterState<*, Child>>
    val model: Value<Model>

    fun onTabClick(tab: Tab)

    data class Model(
        val selectedTab: Tab = Tab.A
    )

    enum class Tab {
        A, B, C
    }

    sealed class Child {
        class ScreenA(val component: IScreenA) : Child()
        class ScreenB(val component: IScreenB) : Child()
        class ScreenC(val component: IScreenC) : Child()
    }
}