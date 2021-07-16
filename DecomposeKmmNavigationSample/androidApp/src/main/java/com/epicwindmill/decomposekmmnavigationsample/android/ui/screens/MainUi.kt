package com.epicwindmill.decomposekmmnavigationsample.android

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.arkivanov.decompose.extensions.compose.jetpack.asState
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.ScreenAUi
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.ScreenBUi
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.ScreenCUi
import com.epicwindmill.decomposekmmnavigationsample.components.main.IMain

@ExperimentalDecomposeApi
@ExperimentalComposeUiApi
@Composable
fun MainUi(main: IMain) {
    val model by main.model.asState()
    Scaffold(
        topBar = { TopBar(
            title = model.selectedTab.name + " Tab"
        ) },
        bottomBar = { BottomNavigationBar(
            selectedTab = model.selectedTab,
            onClick = main::onTabClick
        ) }
    ) {
        Children(
            routerState = main.routerState,
            animation = crossfade()
        ) {
            Log.d("MainUi", "nav: {${it.instance.toString()}}")
            when (val child = it.instance) {
                is IMain.Child.ScreenA -> ScreenAUi(child.component)
                is IMain.Child.ScreenB -> ScreenBUi(child.component)
                is IMain.Child.ScreenC -> ScreenCUi(child.component)
            }
        }
    }
}

@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp)
        }
    )
}

@Composable
fun BottomNavigationBar(
    selectedTab: IMain.Tab,
    onClick: (IMain.Tab) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(painterResource(id = R.drawable.ic_home), contentDescription = "first tab")
            },
            label = { Text(text = "A") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.A,
            onClick = {
                onClick(IMain.Tab.A)
            }
        )
        BottomNavigationItem(
            icon = { Icon(
                painterResource(id = R.drawable.ic_list), contentDescription = "second tab") },
            label = { Text(text = "B") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.B,
            onClick = {
                onClick(IMain.Tab.B)
            }
        )
        BottomNavigationItem(
            icon = { Icon(
                painterResource(id = R.drawable.ic_feedback), contentDescription = "third tab"
            ) },
            label = { Text(text = "C") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.C,
            onClick = {
                onClick(IMain.Tab.C)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("Test title")
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(IMain.Tab.A, {})
}