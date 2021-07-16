package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.epicwindmill.decomposekmmnavigationsample.components.root.IRoot

@ExperimentalComposeUiApi
@ExperimentalDecomposeApi
@Composable
fun RootUi(root: IRoot) {
    Children(
        routerState = root.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is IRoot.Child.Main -> MainUi(child.component)
        }
    }
}