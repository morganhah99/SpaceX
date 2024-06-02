package com.example.common.nav.routes

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.gson.Gson

sealed class RocketNavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Details : RocketNavRoutes(
        route = "$ROUTE_ROCKET_DETAILS/{$ARG_ROCKET_DATA}",
        arguments = listOf(
            navArgument(ARG_ROCKET_DATA) { type = NavType.StringType }
        )
    ) {
        fun routeForRocket(input: RocketInput): String {
            val json = Uri.encode(input.toJson())
            return "$ROUTE_ROCKET_DETAILS/$json"
        }

        fun fromEntry(entry: NavBackStackEntry): RocketInput {
            val json = entry.arguments?.getString(ARG_ROCKET_DATA) ?: ""
            return RocketInput.fromJson(json)
        }
    }

    companion object {
        const val ROUTE_ROCKET_DETAILS = "rocketDetails"
        const val ARG_ROCKET_DATA = "rocketData"
    }
}

data class RocketInput(
    val company: String?,
    val description: String?,
    val costPerLaunch: Int?,
    val rocketType: String?,
    val country: String?
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): RocketInput {
            return Gson().fromJson(json, RocketInput::class.java)
        }
    }
}