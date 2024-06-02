package com.example.common.nav.routes

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.gson.Gson

sealed class ShipNavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Details : ShipNavRoutes(
        route = "$ROUTE_SHIP_DETAILS/{$ARG_SHIP_DATA}",
        arguments = listOf(
            navArgument(ARG_SHIP_DATA) { type = NavType.StringType }
        )
    ) {
        fun routeForShip(input: ShipInput): String {
            val json = Uri.encode(input.toJson())
            return "$ROUTE_SHIP_DETAILS/$json"
        }

        fun fromEntry(entry: NavBackStackEntry): ShipInput {
            val json = entry.arguments?.getString(ARG_SHIP_DATA) ?: ""
            return ShipInput.fromJson(json)
        }
    }

    companion object {
        const val ROUTE_SHIP_DETAILS = "shipDetails"
        const val ARG_SHIP_DATA = "shipData"
    }
}

data class ShipInput(
    val model: String?,
    val shipName: String?,
    val status: String?,
    val shipType: String?,
    val image: String?,
    val weight: Int?,
    val yearBuilt: Int?
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): ShipInput {
            return Gson().fromJson(json, ShipInput::class.java)
        }
    }
}