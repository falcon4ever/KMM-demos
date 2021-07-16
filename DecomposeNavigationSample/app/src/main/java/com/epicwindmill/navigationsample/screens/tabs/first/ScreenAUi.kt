package com.epicwindmill.navigationsample.screens.tabs.first

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.ScreenA1Ui
import com.epicwindmill.navigationsample.screens.tabs.first.screena2.ScreenA2Ui

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun ScreenAUi(screenA: IScreenA) {
    Children(
    routerState = screenA.routerState,
    animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IScreenA.Child.ScreenA1 -> ScreenA1Ui(child.component)
            is IScreenA.Child.ScreenA2 -> ScreenA2Ui(child.component)
        }
    }
}