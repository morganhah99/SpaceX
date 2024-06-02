package com.example.data.remote.model.rocket


import com.google.gson.annotations.SerializedName

data class RocketItemModel(
    @SerializedName("active")
    val active: Boolean? = false,
    @SerializedName("boosters")
    val boosters: Int? = 0,
    @SerializedName("company")
    val company: String? = "",
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int? = 0,
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("first_flight")
    val firstFlight: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("rocket_id")
    val rocketId: String? = "",
    @SerializedName("rocket_name")
    val rocketName: String? = "",
    @SerializedName("rocket_type")
    val rocketType: String? = "",
    @SerializedName("stages")
    val stages: Int? = 0,
    @SerializedName("success_rate_pct")
    val successRatePct: Int? = 0,
    @SerializedName("wikipedia")
    val wikipedia: String? = ""
)