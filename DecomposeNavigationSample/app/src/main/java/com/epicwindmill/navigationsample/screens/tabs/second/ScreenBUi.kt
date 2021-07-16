package com.epicwindmill.navigationsample.screens.tabs.second

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.ScreenB1Ui
import com.epicwindmill.navigationsample.screens.tabs.second.screenb2.ScreenB2Ui

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun ScreenBUi(screenB: IScreenB) {
    Children(
        routerState = screenB.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IScreenB.Child.ScreenB1 -> ScreenB1Ui(child.component)
            is IScreenB.Child.ScreenB2 -> ScreenB2Ui(child.component)
        }
    }
}