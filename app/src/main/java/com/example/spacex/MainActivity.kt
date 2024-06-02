package com.example.spacex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.common.nav.NavRoutes
import com.example.spacex.ui.compose.auth.AuthScreen
import com.example.spacex.ui.compose.auth.signOut
import com.example.spacex.ui.compose.home.HomeScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleDetailsScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleListScreen
import com.example.spacex.ui.compose.list.history.HistoryListScreen
import com.example.spacex.ui.compose.list.launch.LaunchListScreen
import com.example.spacex.ui.compose.list.mission.MissionListScreen
import com.example.spacex.ui.compose.list.rocket.RocketListScreen
import com.example.spacex.ui.compose.list.ship.ShipListScreen
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
    val topBarState = remember { mutableStateOf(false) }
    val bottomBarState = remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()



    when (navBackStackEntry?.destination?.route) {
        NavRoutes.Home.route,
        NavRoutes.Home.route -> {
            bottomBarState.value = true
            topBarState.value = false
        }
        else -> {
            bottomBarState.value = false
            topBarState.value = true
        }
    }

    Scaffold(
        topBar = {
            if (topBarState.value) {
                TopAppBar(
                    title = { Text(text = "Details") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        },
        bottomBar = { BottomAppBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.ROUTE_AUTH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.ROUTE_AUTH) {
                AuthScreen(navController = navController)
            }
            composable(NavRoutes.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(NavRoutes.ROUTE_CAPSULES) {
                CapsuleListScreen(hiltViewModel(), navController = navController)
            }
            composable(
                route = NavRoutes.Capsule.route,
                arguments = NavRoutes.Capsule.arguments
            ) {
                CapsuleDetailsScreen(NavRoutes.Capsule.fromEntry(it))
            }
            composable(NavRoutes.ROUTE_HISTORY) {
                HistoryListScreen(hiltViewModel())
            }
            composable(NavRoutes.ROUTE_MISSIONS) {
                MissionListScreen(hiltViewModel(), navController = navController)
            }
            composable(NavRoutes.ROUTE_ROCKETS) {
                RocketListScreen(hiltViewModel(), navController = navController)
            }
            composable(NavRoutes.ROUTE_LAUNCHES) {
                LaunchListScreen(hiltViewModel())
            }
            composable(NavRoutes.ROUTE_SHIPS) {
                ShipListScreen(hiltViewModel())
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
            icon = {
                Icon(
                    painter = painterResource(R.drawable.logout_24dp_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            },
            label = { Text("Sign Out") }
        )
    }
}
