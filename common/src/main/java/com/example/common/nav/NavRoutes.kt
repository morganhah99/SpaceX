package com.example.common.nav

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.common.nav.input.CapsuleInput
import com.example.common.nav.input.CapsuleNavRoutes
import com.example.common.nav.input.HistoryNavRoutes
import com.example.common.nav.input.LaunchNavRoutes
import com.example.common.nav.input.MissionNavRoutes

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : NavRoutes(ROUTE_HOME)
    object Capsule : NavRoutes(CapsuleNavRoutes.Details.route, CapsuleNavRoutes.Details.arguments)

    object History : NavRoutes(HistoryNavRoutes.Details.route, HistoryNavRoutes.Details.arguments)
    object Launch : NavRoutes(LaunchNavRoutes.Details.route, LaunchNavRoutes.Details.arguments)

    object Mission : NavRoutes(MissionNavRoutes.Details.route, MissionNavRoutes.Details.arguments)




    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_CAPSULES = "capsules"
        const val ROUTE_MISSIONS = "missions"
        const val ROUTE_LAUNCHES = "launches"
        const val ROUTE_ROCKETS = "rockets"
        const val ROUTE_SHIPS = "ships"
        const val ROUTE_AUTH = "auth"
        const val ROUTE_HISTORY = "history"

    }
}
