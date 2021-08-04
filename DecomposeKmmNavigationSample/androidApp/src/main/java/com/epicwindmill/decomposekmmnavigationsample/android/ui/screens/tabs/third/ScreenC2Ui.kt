package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.third

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc2.IScreenC2

@Composable
fun ScreenC2Ui(component: IScreenC2) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen C2"
            )
        }
    }
}