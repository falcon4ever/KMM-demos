package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.first

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.IScreenA

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun ScreenAUi(component: IScreenA) {
    Children(
        routerState = component.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IScreenA.Child.ScreenA1 -> ScreenA1Ui(child.component)
            is IScreenA.Child.ScreenA2 -> ScreenA2Ui(child.component)
        }
    }
}