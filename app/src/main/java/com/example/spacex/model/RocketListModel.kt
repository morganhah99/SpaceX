package com.example.spacex.model

data class RocketListModel(
    val items: List<Rocket>
)

data class Rocket(
    val active: Boolean? = false,
    val boosters: Int? = 0,
    val company: String? = "",
    val costPerLaunch: Int? = 0,
    val country: String? = "",
    val description: String? = "",
    val firstFlight: String? = "",
    val id: Int? = 0,
    val mass: Mass? = Mass(),
    val rocketId: String? = "",
    val rocketName: String? = "",
    val rocketType: String? = "",
    val stages: Int = 0,
    val successRatePct: Int? = 0,
    val wikipedia: String = ""
)

data class Diameter(val meters: Double? = 0.0, val feet: Double? = 0.0)
data class Engines(val number: Int = 0, val type: String = "", val version: String = "")
data class FirstStage(val cores: Int = 0, val fuelAmountTons: Double = 0.0, val burnTimeSec: Int = 0)
data class Height(val meters: Double? = 0.0, val feet: Double? = 0.0)
data class LandingLegs(val number: Int = 0, val material: String? = "")
data class Mass(val kg: Int = 0, val lb: Int = 0)
data class PayloadWeight(val id: String = "", val name: String = "", val kg: Int = 0, val lb: Int = 0)
data class SecondStage(val engines: Int = 0, val fuelAmountTons: Double = 0.0, val burnTimeSec: Int = 0)
