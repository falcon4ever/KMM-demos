package com.epicwindmill.navigationsample.screens.tabs.second

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.IScreenB1
import com.epicwindmill.navigationsample.screens.tabs.second.screenb2.IScreenB2

interface IScreenB {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class ScreenB1(val component: IScreenB1) : Child()
        class ScreenB2(val component: IScreenB2) : Child()
    }
}