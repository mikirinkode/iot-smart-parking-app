package com.mikirinkode.smartparking

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mikirinkode.smartparking.ui.screen.HomeScreen
import com.mikirinkode.smartparking.ui.screen.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Main: Screen ("main") // consist of Welcome and Home Screen
}

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val startScreen = Screen.Main.route

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Main.route) {
                MainRoute()
            }
        }
    }
}

@Composable
fun MainRoute() {
    var mainScreenState by rememberSaveable { mutableIntStateOf(0) }
    AnimatedContent(
        targetState = mainScreenState,
        transitionSpec = {
            val animationSpec: TweenSpec<IntOffset> = tween(1000)
            val direction = if (mainScreenState == 1) {
                AnimatedContentTransitionScope.SlideDirection.Right
            } else {
                AnimatedContentTransitionScope.SlideDirection.Left
            }
            slideIntoContainer(
                towards = direction,
                animationSpec = animationSpec,
            ) togetherWith slideOutOfContainer(
                towards = direction,
                animationSpec = animationSpec
            )
        },
        label = "mainScreenDataAnimation",
    ) { targetState ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (targetState) {
                0 -> {
                    WelcomeScreen(
                        onStartClicked = {
                            mainScreenState = 1
                        }
                    )
                }
                else -> {
                    HomeScreen()
                }
            }
        }
    }
}