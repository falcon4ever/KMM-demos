package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.second

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
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1.IScreenB1

@Composable
fun ScreenB1Ui(screenB1: IScreenB1) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen B1",
                modifier = Modifier.padding(32.dp)
            )
            Button(onClick = {
                screenB1.navigateToB2Clicked()
            }) {
                Text(
                    text = "Go to Screen B2",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}