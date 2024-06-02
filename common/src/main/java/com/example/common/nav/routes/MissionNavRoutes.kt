package com.example.common.nav.routes

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.gson.Gson

sealed class MissionNavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Details : MissionNavRoutes(
        route = "$ROUTE_MISSION_DETAILS/{$ARG_MISSION_DATA}",
        arguments = listOf(
            navArgument(ARG_MISSION_DATA) { type = NavType.StringType }
        )
    ) {
        fun routeForMission(input: MissionInput): String {
            val json = Uri.encode(input.toJson())
            return "$ROUTE_MISSION_DETAILS/$json"
        }

        fun fromEntry(entry: NavBackStackEntry): MissionInput {
            val json = entry.arguments?.getString(ARG_MISSION_DATA) ?: ""
            return MissionInput.fromJson(json)
        }
    }

    companion object {
        const val ROUTE_MISSION_DETAILS = "missionDetails"
        const val ARG_MISSION_DATA = "missionData"
    }
}

data class MissionInput(
    val descriptions: String?
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): MissionInput {
            return Gson().fromJson(json, MissionInput::class.java)
        }
    }
}