package com.example.domain.entity

data class Rocket(
    val active: Boolean? = false,
    val boosters: Int? = 0,
    val company: String? = "",
    val costPerLaunch: Int? = 0,
    val country: String? = "",
    val description: String? = "",
    val firstFlight: String? = "",
    val flickrImages: List<String> = listOf(),
    val id: Int? = 0,
    val rocketId: String? = "",
    val rocketName: String? = "",
    val rocketType: String? = "",
    val stages: Int = 0,
    val successRatePct: Int? = 0,
    val wikipedia: String = ""
)


