package com.kevin.desksetupapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kevin.desksetupapp.ui.navigation.Screen
import com.kevin.desksetupapp.ui.screen.detail.DetailScreen
import com.kevin.desksetupapp.ui.screen.home.HomeScreen
import com.kevin.desksetupapp.ui.screen.profile.ProfileScreen
import com.kevin.desksetupapp.ui.theme.SubmissionApplicationTheme

@Composable
fun DeskSetup(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
            topBar = {
                if (currentRoute == Screen.Home.route) {
                    TopBar(
                            navController = navController,
                            modifier = modifier.padding(start = 10.dp)
                    )
                }
            }
    ) { padding ->
        NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                        navigateToDetail = { id ->
                            navController.navigate(Screen.Detail.createRoute(id))
                        }
                )
            }
            composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("id") ?: -1L
                DetailScreen(id = id, navigateBack = { navController.navigateUp() })
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen(onBackClick = { navController.navigateUp() })
            }
        }
    }
}

@Composable
fun TopBar(navController: NavHostController, modifier: Modifier) {
    TopAppBar(backgroundColor = Color.DarkGray) {
        Column(modifier = modifier.fillMaxSize().weight(0.8f)) {
            Text(text = "DeskSetup App", fontSize = 24.sp, color = Color.White)
        }
        IconButton(
                onClick = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "about_page",
                    tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeskSetupPreview() {
    SubmissionApplicationTheme { DeskSetup() }
}
