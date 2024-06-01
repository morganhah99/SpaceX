package com.example.spacex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common.nav.NavRoutes
import com.example.spacex.ui.compose.home.HomeScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleDetailsScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleListScreen
import com.example.spacex.ui.compose.list.history.HistoryListScreen
import com.example.spacex.ui.theme.SpaceXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                val navController = rememberNavController()
                App(navController = navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomAppBar(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Home.route
        ) {
            composable(NavRoutes.Home.route){
                HomeScreen(navController = navController)
            }
            composable(NavRoutes.ROUTE_CAPSULES){
                CapsuleListScreen(hiltViewModel(), navController = navController )
            }
            composable(
                route = NavRoutes.Capsule.route,
                arguments = NavRoutes.Capsule.arguments
            ) {
                CapsuleDetailsScreen(NavRoutes.Capsule.fromEntry(it))
            }
            composable(NavRoutes.ROUTE_HISTORY){
                HistoryListScreen(hiltViewModel())
            }
        }
    }
}




@Composable
fun BottomAppBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            selected = navController.currentBackStackEntry?.destination?.route == NavRoutes.Home.route,
            onClick = { navController.navigate(NavRoutes.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") }
        )
    }
}
