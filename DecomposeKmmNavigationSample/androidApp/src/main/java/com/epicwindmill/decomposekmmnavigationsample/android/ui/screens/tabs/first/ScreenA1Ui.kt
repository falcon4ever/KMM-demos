package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.first

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena1.IScreenA1

@Composable
fun ScreenA1Ui(component: IScreenA1) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Screen A1",
                modifier = Modifier.padding(32.dp)
            )
            Button(onClick = {
                component.navigateToA2Clicked()
            }) {
                Text(
                    text = "Go to Screen A2",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}