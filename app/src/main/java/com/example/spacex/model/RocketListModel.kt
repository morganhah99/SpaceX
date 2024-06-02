package com.example.spacex.model

data class RocketListModel(
    val items: List<Rocket>
)

data class Rocket(
    val company: String? = "",
    val costPerLaunch: Int? = 0,
    val country: String? = "",
    val description: String? = "",
    val id: Int? = 0,
    val rocketId: String? = "",
    val rocketName: String? = "",
    val rocketType: String? = "",
)

