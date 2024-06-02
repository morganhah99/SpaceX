package com.example.spacex.model


data class LaunchListModel(
    val items: List<Launch> = listOf()
)

data class Launch(
    val details: String? = "",
    val flightNumber: Int? = 0,
    val launchSuccess: Boolean? = false,
    val launchYear: String? = "",
    val missionName: String? = "",
)