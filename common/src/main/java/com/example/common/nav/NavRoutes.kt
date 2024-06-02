package com.example.common.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : NavRoutes(ROUTE_HOME)
    data object Capsule : NavRoutes(
        route = "$ROUTE_CAPSULE_DETAILS/{$ARG_CAPSULE_SERIAL}",
        arguments = listOf(
            navArgument(ARG_CAPSULE_SERIAL) { type = NavType.StringType }
        )
    ) {
        fun routeForCapsule(input: CapsuleInput) =
            "$ROUTE_CAPSULE_DETAILS/${input.serialId}"

        fun fromEntry(entry: NavBackStackEntry): CapsuleInput {
            return CapsuleInput(
                entry.arguments?.getString(ARG_CAPSULE_SERIAL) ?: ""
            )
        }
    }

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_CAPSULES = "capsules"
        const val ROUTE_MISSIONS = "missions"
        const val ROUTE_LAUNCHES = "launches"
        const val ROUTE_ROCKETS = "rockets"
        const val ROUTE_AUTH = "auth"
        const val ROUTE_HISTORY = "history"
        const val ROUTE_CAPSULE_DETAILS = "capsuleDetails"
        const val ARG_CAPSULE_SERIAL = "capsule_serial"
    }
}
