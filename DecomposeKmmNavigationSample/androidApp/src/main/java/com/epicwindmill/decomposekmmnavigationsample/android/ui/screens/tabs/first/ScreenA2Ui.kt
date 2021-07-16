package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena2.IScreenA2

@Composable
fun ScreenA2Ui(screenA2: IScreenA2) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen A2"
            )
        }
    }
}