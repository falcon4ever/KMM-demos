package com.epicwindmill.decomposekmmnavigationsample.components.tabs.first

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena1.IScreenA1
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena2.IScreenA2

interface IScreenA {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class ScreenA1(val component: IScreenA1) : Child()
        class ScreenA2(val component: IScreenA2) : Child()
    }
}