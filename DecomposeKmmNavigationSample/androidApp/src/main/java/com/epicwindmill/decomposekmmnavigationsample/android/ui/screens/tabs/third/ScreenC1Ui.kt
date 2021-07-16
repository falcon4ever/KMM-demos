package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.third

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc1.IScreenC1

@Composable
fun ScreenC1Ui(screenC1: IScreenC1) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen C1",
                modifier = Modifier.padding(32.dp)
            )
            Button(onClick = {
                screenC1.navigateToC2Clicked()
            }) {
                Text(
                    text = "Go to Screen C2",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}