package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.third

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.IScreenC

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun ScreenCUi(component: IScreenC) {
    Children(
        routerState = component.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IScreenC.Child.ScreenC1 -> ScreenC1Ui(child.component)
            is IScreenC.Child.ScreenC2 -> ScreenC2Ui(child.component)
        }
    }
}