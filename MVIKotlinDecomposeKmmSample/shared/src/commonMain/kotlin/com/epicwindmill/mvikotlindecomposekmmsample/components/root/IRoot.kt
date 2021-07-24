package com.epicwindmill.mvikotlindecomposekmmsample.components.root

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.IMain

interface IRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Main(val component: IMain) : Child()
    }
}