package com.example.spacex.model

data class MissionListModel (
    val items: List<Mission>
)


data class Mission(
    val description: String? = "",
    val manufacturers: List<String?>? = listOf(),
    val missionId: String? = "",
    val missionName: String? = "",
    val payloadIds: List<String?>? = listOf(),
    val twitter: String? = "",
    val website: String? = "",
    val wikipedia: String? = ""
)