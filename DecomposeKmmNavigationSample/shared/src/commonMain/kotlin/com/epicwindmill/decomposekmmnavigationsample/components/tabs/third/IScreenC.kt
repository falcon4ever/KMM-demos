package com.epicwindmill.decomposekmmnavigationsample.components.tabs.third

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc1.IScreenC1
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc2.IScreenC2

interface IScreenC {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class ScreenC1(val component: IScreenC1) : Child()
        class ScreenC2(val component: IScreenC2) : Child()
    }
}