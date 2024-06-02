package com.example.domain.entity

data class Launch(
    val details: String? = "",
    val flightNumber: Int? = 0,
    val isTentative: Boolean? = false,
    val lastDateUpdate: String? = "",
    val lastLlLaunchDate: String? = "",
    val lastLlUpdate: String? = "",
    val lastWikiLaunchDate: String? = "",
    val lastWikiRevision: String? = "",
    val lastWikiUpdate: String? = "",
    val launchDateLocal: String? = "",
    val launchDateSource: String? = "",
    val launchDateUnix: Int? = 0,
    val launchDateUtc: String? = "",
    val launchSuccess: Boolean? = false,
    val launchWindow: Int? = 0,
    val launchYear: String? = "",
    val missionId: List<String?>? = listOf(),
    val missionName: String? = "",
    val ships: List<String?>? = listOf(),
    val staticFireDateUnix: Int? = 0,
    val staticFireDateUtc: String? = "",
    val tbd: Boolean? = false,
    val tentativeMaxPrecision: String? = "",
    val upcoming: Boolean? = false
)