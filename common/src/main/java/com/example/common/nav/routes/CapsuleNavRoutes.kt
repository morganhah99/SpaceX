package com.example.common.nav.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.google.gson.Gson
import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType

sealed class CapsuleNavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Details : CapsuleNavRoutes(
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
        const val ROUTE_CAPSULE_DETAILS = "capsuleDetails"
        const val ARG_CAPSULE_DATA = "capsule_data"
    }
}

data class CapsuleInput(
    val serialId: String?,
    val details: String?,
    val status: String?,
    val landings: Int?,
    val type: String?,
    val launch: String?
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): CapsuleInput {
            return Gson().fromJson(json, CapsuleInput::class.java)
        }
    }
}