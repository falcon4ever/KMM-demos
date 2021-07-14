package com.epicwindmill.navigationsample.screens.root

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.navigationsample.screens.main.IMain

interface IRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Main(val component: IMain) : Child()
    }
}