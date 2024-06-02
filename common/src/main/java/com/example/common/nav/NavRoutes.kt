package com.example.common.nav

import androidx.navigation.NamedNavArgument
import com.example.common.nav.routes.CapsuleNavRoutes
import com.example.common.nav.routes.HistoryNavRoutes
import com.example.common.nav.routes.LaunchNavRoutes
import com.example.common.nav.routes.MissionNavRoutes
import com.example.common.nav.routes.RocketNavRoutes
import com.example.common.nav.routes.ShipNavRoutes

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : NavRoutes(ROUTE_HOME)
    object Capsule : NavRoutes(CapsuleNavRoutes.Details.route, CapsuleNavRoutes.Details.arguments)

    object History : NavRoutes(HistoryNavRoutes.Details.route, HistoryNavRoutes.Details.arguments)
    object Launch : NavRoutes(LaunchNavRoutes.Details.route, LaunchNavRoutes.Details.arguments)
    object Mission : NavRoutes(MissionNavRoutes.Details.route, MissionNavRoutes.Details.arguments)
    object Rocket : NavRoutes(RocketNavRoutes.Details.route, RocketNavRoutes.Details.arguments)
    object Ship : NavRoutes(ShipNavRoutes.Details.route, ShipNavRoutes.Details.arguments)



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
