package com.mikirinkode.smartparking

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
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
import com.mikirinkode.smartparking.ui.screen.ParkingDetailScreen
import com.mikirinkode.smartparking.ui.screen.ParkingListScreen
import com.mikirinkode.smartparking.ui.screen.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Detail : Screen("detail")
}

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val startScreen = Screen.Welcome.route

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startScreen,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }

        ) {
            composable(route = Screen.Welcome.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500, easing = EaseIn)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500, easing = EaseIn)
                    )
                }
            ) {
                WelcomeScreen(onStartClicked = { navController.navigate(Screen.Home.route) })
            }
            composable(
                route = Screen.Home.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500, easing = EaseIn)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(500, easing = EaseIn)
                    )
                }
            ) {
                ParkingListScreen(onItemClicked = { navController.navigate(Screen.Detail.route) })
            }

            composable(
                route = Screen.Detail.route,
//                enterTransition = {
//                    slideIntoContainer(
//                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
//                        animationSpec = tween(500, easing = EaseIn)
//                    )
//                },
//                exitTransition = {
//                    slideOutOfContainer(
//                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
//                        animationSpec = tween(500, easing = EaseIn)
//                    )
//                }
            ) {
                ParkingDetailScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}