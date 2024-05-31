package com.example.common.nav

import androidx.navigation.NamedNavArgument

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Home : NavRoutes(ROUTE_HOME)


    companion object {
        const val ROUTE_HOME = "home"
    }

}