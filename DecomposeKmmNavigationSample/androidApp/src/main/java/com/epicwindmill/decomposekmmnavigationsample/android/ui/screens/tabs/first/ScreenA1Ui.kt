package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens

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
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena1.IScreenA1

@Composable
fun ScreenA1Ui(screenA: IScreenA1) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen A1",
                modifier = Modifier.padding(32.dp)
            )
            Button(onClick = {
                screenA.navigateToA2Clicked()
            }) {
                Text(
                    text = "Go to Screen A2",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}