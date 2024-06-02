package com.example.common.nav.routes

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.gson.Gson

sealed class LaunchNavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Details : LaunchNavRoutes(
        route = "$ROUTE_LAUNCH_DETAILS/{$ARG_LAUNCH_DATA}",
        arguments = listOf(
            navArgument(ARG_LAUNCH_DATA) { type = NavType.StringType }
        )
    ) {
        fun routeForLaunch(input: LaunchInput): String {
            val json = Uri.encode(input.toJson())
            return "$ROUTE_LAUNCH_DETAILS/$json"
        }

        fun fromEntry(entry: NavBackStackEntry): LaunchInput {
            val json = entry.arguments?.getString(ARG_LAUNCH_DATA) ?: ""
            return LaunchInput.fromJson(json)
        }
    }

    companion object {
        const val ROUTE_LAUNCH_DETAILS = "launchDetails"
        const val ARG_LAUNCH_DATA = "launchDetails"
    }
}

data class LaunchInput(
    val details: String?,
    val success: Boolean?,
    val missionName: String?,
    val launchYear: String?
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): LaunchInput {
            return Gson().fromJson(json, LaunchInput::class.java)
        }
    }
}