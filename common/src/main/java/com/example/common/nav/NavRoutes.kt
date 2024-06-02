package com.example.common.nav

import android.net.Uri
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
        route = "$ROUTE_CAPSULE_DETAILS/{$ARG_CAPSULE_DATA}",
        arguments = listOf(
            navArgument(ARG_CAPSULE_DATA) { type = NavType.StringType }
        )
    ) {
        fun routeForCapsule(input: CapsuleInput): String {
            val json = Uri.encode(input.toJson())
            return "$ROUTE_CAPSULE_DETAILS/$json"
        }

        fun fromEntry(entry: NavBackStackEntry): CapsuleInput {
            val json = entry.arguments?.getString(ARG_CAPSULE_DATA) ?: ""
            return CapsuleInput.fromJson(json)
        }
    }

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_CAPSULES = "capsules"
        const val ROUTE_MISSIONS = "missions"
        const val ROUTE_LAUNCHES = "launches"
        const val ROUTE_ROCKETS = "rockets"
        const val ROUTE_SHIPS = "ships"
        const val ROUTE_AUTH = "auth"
        const val ROUTE_HISTORY = "history"
        const val ARG_CAPSULE_DATA = "capsule_data"
        const val ROUTE_CAPSULE_DETAILS = "capsuleDetails"
        const val ARG_CAPSULE_SERIAL = "capsule_serial"
        const val ARG_CAPSULE_DETAILS = "capsule_details"

    }
}
