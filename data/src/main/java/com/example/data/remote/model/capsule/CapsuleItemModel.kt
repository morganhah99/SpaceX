package com.example.data.remote.model.capsule


import com.google.gson.annotations.SerializedName

data class CapsuleItemModel(
    @SerializedName("capsule_id")
    val capsuleId: String? = "",
    @SerializedName("capsule_serial")
    val capsuleSerial: String? = "",
    @SerializedName("details")
    val details: String? = "",
    @SerializedName("landings")
    val landings: Int? = 0,
    @SerializedName("missions")
    val missions: List<MissionModel?>? = listOf(),
    @SerializedName("original_launch")
    val originalLaunch: String? = "",
    @SerializedName("original_launch_unix")
    val originalLaunchUnix: Int? = 0,
    @SerializedName("reuse_count")
    val reuseCount: Int? = 0,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("type")
    val type: String? = ""
)