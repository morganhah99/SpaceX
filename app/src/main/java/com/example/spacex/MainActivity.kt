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
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common.nav.NavRoutes
import com.example.spacex.ui.compose.auth.AuthScreen
import com.example.spacex.ui.compose.auth.signOut
import com.example.spacex.ui.compose.home.HomeScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleDetailsScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleListScreen
import com.example.spacex.ui.compose.list.history.HistoryListScreen
import com.example.spacex.ui.theme.SpaceXTheme
import com.google.firebase.auth.FirebaseAuth
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
            startDestination = NavRoutes.ROUTE_AUTH
        ) {
            composable(NavRoutes.ROUTE_AUTH) {
                AuthScreen(navController = navController)
            }
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
    val auth = FirebaseAuth.getInstance()
    BottomNavigation {
        BottomNavigationItem(
            selected = navController.currentBackStackEntry?.destination?.route == NavRoutes.Home.route,
            onClick = { signOut(auth, navController) },
            icon = { Icon(
                painter = painterResource(R.drawable.logout_24dp_fill0_wght400_grad0_opsz24),
                contentDescription = null
            ) },
            label = { Text("Sign Out") }
        )
    }
}
