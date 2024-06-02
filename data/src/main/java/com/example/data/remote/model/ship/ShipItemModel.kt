package com.example.data.remote.model.ship


import com.google.gson.annotations.SerializedName

data class ShipItemModel(
    @SerializedName("abs")
    val abs: Int? = 0,
    @SerializedName("active")
    val active: Boolean? = false,
    @SerializedName("attempted_catches")
    val attemptedCatches: Int? = 0,
    @SerializedName("attempted_landings")
    val attemptedLandings: Int? = 0,
    @SerializedName("class")
    val classX: Int? = 0,
    @SerializedName("home_port")
    val homePort: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("imo")
    val imo: Int? = 0,
    @SerializedName("mmsi")
    val mmsi: Int? = 0,
    @SerializedName("roles")
    val roles: List<String?>? = listOf(),
    @SerializedName("ship_id")
    val shipId: String? = "",
    @SerializedName("ship_model")
    val shipModel: String? = "",
    @SerializedName("ship_name")
    val shipName: String? = "",
    @SerializedName("ship_type")
    val shipType: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("successful_catches")
    val successfulCatches: Int? = 0,
    @SerializedName("successful_landings")
    val successfulLandings: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("weight_kg")
    val weightKg: Int? = 0,
    @SerializedName("weight_lbs")
    val weightLbs: Int? = 0,
    @SerializedName("year_built")
    val yearBuilt: Int? = 0
)