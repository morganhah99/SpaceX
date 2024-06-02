package com.example.domain.entity


data class Ship(
    val abs: Int? = 0,
    val active: Boolean? = false,
    val attemptedCatches: Int? = 0,
    val attemptedLandings: Int? = 0,
    val classX: Int? = 0,
    val homePort: String? = "",
    val image: String? = "",
    val imo: Int? = 0,
    val mmsi: Int? = 0,
    val roles: List<String?>? = listOf(),
    val shipId: String? = "",
    val shipModel: String? = "",
    val shipName: String? = "",
    val shipType: String? = "",
    val status: String? = "",
    val successfulCatches: Int? = 0,
    val successfulLandings: Int? = 0,
    val url: String? = "",
    val weightKg: Int? = 0,
    val weightLbs: Int? = 0,
    val yearBuilt: Int? = 0
)
