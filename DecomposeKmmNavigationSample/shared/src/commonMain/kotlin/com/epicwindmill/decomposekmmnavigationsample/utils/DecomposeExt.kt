package com.epicwindmill.decomposekmmnavigationsample.utils

import com.arkivanov.decompose.Router
import com.arkivanov.decompose.statekeeper.Parcelable

inline fun <C : Parcelable, reified T : C> Router<C, *>.navigateSingleTop(crossinline config: () -> T) {
    navigate { stack ->
        val oldConfig: C? = stack.find { it is T }
        if (oldConfig != null) {
            (stack - oldConfig) + oldConfig
        } else {
            stack + config()
        }
    }
}
