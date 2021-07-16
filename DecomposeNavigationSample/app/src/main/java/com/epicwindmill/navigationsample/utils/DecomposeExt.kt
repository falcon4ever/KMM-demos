package com.epicwindmill.navigationsample.utils

import android.os.Parcelable
import com.arkivanov.decompose.Router

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
