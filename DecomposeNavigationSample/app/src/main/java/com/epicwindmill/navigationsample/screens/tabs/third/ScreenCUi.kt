package com.epicwindmill.navigationsample.screens.tabs.third

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.navigationsample.screens.tabs.third.screenc1.ScreenC1Ui
import com.epicwindmill.navigationsample.screens.tabs.third.screenc2.ScreenC2Ui

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun ScreenCUi(screenC: IScreenC) {
    Children(
        routerState = screenC.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IScreenC.Child.ScreenC1 -> ScreenC1Ui(child.component)
            is IScreenC.Child.ScreenC2 -> ScreenC2Ui(child.component)
        }
    }
}