package com.example.spacex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.common.nav.NavRoutes
import com.example.common.nav.routes.CapsuleNavRoutes
import com.example.common.nav.routes.HistoryNavRoutes
import com.example.common.nav.routes.LaunchNavRoutes
import com.example.common.nav.routes.MissionNavRoutes
import com.example.common.nav.routes.RocketNavRoutes
import com.example.common.nav.routes.ShipNavRoutes
import com.example.spacex.ui.compose.auth.AuthScreen
import com.example.spacex.ui.compose.auth.signOut
import com.example.spacex.ui.compose.home.HomeScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleDetailsScreen
import com.example.spacex.ui.compose.list.capsule.CapsuleListScreen
import com.example.spacex.ui.compose.list.history.HistoryDetailsScreen
import com.example.spacex.ui.compose.list.history.HistoryListScreen
import com.example.spacex.ui.compose.list.launch.LaunchDetailsScreen
import com.example.spacex.ui.compose.list.launch.LaunchListScreen
import com.example.spacex.ui.compose.list.mission.MissionDetailsScreen
import com.example.spacex.ui.compose.list.mission.MissionListScreen
import com.example.spacex.ui.compose.list.rocket.RocketDetailsScreen
import com.example.spacex.ui.compose.list.rocket.RocketListScreen
import com.example.spacex.ui.compose.list.ship.ShipDetailsScreen
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
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val topBarTitle = remember(currentRoute) {
        when (currentRoute) {
            NavRoutes.ROUTE_CAPSULES -> "Capsules"
            NavRoutes.Capsule.route -> "Capsule Details"
            NavRoutes.ROUTE_HISTORY -> "History"
            NavRoutes.History.route -> "History Details"
            NavRoutes.ROUTE_MISSIONS -> "Missions"
            NavRoutes.Mission.route -> "Mission Details"
            NavRoutes.ROUTE_ROCKETS -> "Rockets"
            NavRoutes.Rocket.route -> "Rocket Details"
            NavRoutes.ROUTE_LAUNCHES -> "Launches"
            NavRoutes.Launch.route -> "Launch Details"
            NavRoutes.ROUTE_SHIPS -> "Ships"
            NavRoutes.Ship.route -> "Ship Details"
            else -> "Details"
        }
    }


    when (navBackStackEntry?.destination?.route) {
        NavRoutes.Home.route,
        NavRoutes.Home.route -> {
            bottomBarState.value = true
            topBarState.value = false
        }
        NavRoutes.ROUTE_AUTH -> {
            bottomBarState.value = false
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
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    title = { Text(text = topBarTitle) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (bottomBarState.value){
                BottomAppBar(navController)
            }
        }
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
                CapsuleDetailsScreen(CapsuleNavRoutes.Details.fromEntry(it))
            }
            composable(NavRoutes.ROUTE_HISTORY) {
                HistoryListScreen(hiltViewModel(), navController = navController)
            }
            composable(
                route = NavRoutes.History.route,
                arguments = NavRoutes.History.arguments
            ) {
                HistoryDetailsScreen(HistoryNavRoutes.Details.fromEntry(it))
            }
            composable(NavRoutes.ROUTE_MISSIONS) {
                MissionListScreen(hiltViewModel(), navController = navController)
            }
            composable(
                route = NavRoutes.Mission.route,
                arguments = NavRoutes.Mission.arguments
            ) {
                MissionDetailsScreen(MissionNavRoutes.Details.fromEntry(it))
            }
            composable(NavRoutes.ROUTE_ROCKETS) {
                RocketListScreen(hiltViewModel(), navController = navController)
            }
            composable(
                route = NavRoutes.Rocket.route,
                arguments = NavRoutes.Rocket.arguments
            ) {
                RocketDetailsScreen(RocketNavRoutes.Details.fromEntry(it))

            }
            composable(NavRoutes.ROUTE_LAUNCHES) {
                LaunchListScreen(hiltViewModel(), navController = navController)
            }
            composable(
                route = NavRoutes.Launch.route,
                arguments = NavRoutes.Launch.arguments
            ) {
                LaunchDetailsScreen(LaunchNavRoutes.Details.fromEntry(it))
            }
            composable(NavRoutes.ROUTE_SHIPS) {
                ShipListScreen(hiltViewModel(), navController = navController)
            }
            composable(
                route = NavRoutes.Ship.route,
                arguments = NavRoutes.Ship.arguments
            ) {
                ShipDetailsScreen(ShipNavRoutes.Details.fromEntry(it))
            }
        }
    }
}


@Composable
fun BottomAppBar(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(R.drawable.logout_24dp_fill0_wght400_grad0_opsz24),
                contentDescription = null,
                modifier = Modifier
                    .clickable { signOut(auth, navController) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign Out",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .clickable { signOut(auth, navController) }
            )
        }
    }
}
