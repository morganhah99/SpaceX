package com.example.spacex.model

import com.example.domain.entity.Mission

data class CapsuleListModel(
    val items: List<Capsule> = listOf()
)


data class Capsule(

    val capsuleId: String? = "",
    val capsuleSerial: String? = "",
    val details: String? = "",
    val landings: Int? = 0,
    val missions: List<Mission?>? = listOf(),
    val originalLaunch: String? = "",
    val originalLaunchUnix: Int? = 0,
    val reuseCount: Int? = 0,
    val status: String? = "",
    val type: String? = ""

)