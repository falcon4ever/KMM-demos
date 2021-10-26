package com.epicwindmill.decomposekmmnavigationsample.components.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.epicwindmill.decomposekmmnavigationsample.components.main.IMain

interface IRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        class Main(val component: IMain) : Child()
    }
}