package com.mikirinkode.smartparking

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mikirinkode.smartparking.ui.screen.HomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
}

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val startScreen = Screen.Home.route

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen()
            }
        }
    }
}