package com.example.spacex.model

data class CapsuleListModel(
    val items: List<Capsule> = listOf()
)


data class Capsule(
    val capsuleSerial: String? = "",
    val details: String? = "",
    val landings: Int? = 0,
    val originalLaunch: String? = "",
    val status: String? = "",
    val type: String? = ""
)