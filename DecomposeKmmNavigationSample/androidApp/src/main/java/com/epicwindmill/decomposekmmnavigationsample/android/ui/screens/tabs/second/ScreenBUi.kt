package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.second

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.IScreenB

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun ScreenBUi(component: IScreenB) {
    Children(
        routerState = component.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IScreenB.Child.ScreenB1 -> ScreenB1Ui(child.component)
            is IScreenB.Child.ScreenB2 -> ScreenB2Ui(child.component)
        }
    }
}