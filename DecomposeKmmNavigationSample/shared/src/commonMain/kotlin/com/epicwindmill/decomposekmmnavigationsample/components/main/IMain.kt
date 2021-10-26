package com.epicwindmill.decomposekmmnavigationsample.components.main

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.IScreenA
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.IScreenB
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.IScreenC

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