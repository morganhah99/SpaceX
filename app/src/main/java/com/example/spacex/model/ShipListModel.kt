package com.example.spacex.model

data class ShipListModel(
    val items: List<ShipItem> = listOf()
)

data class ShipItem(
    val active: Boolean? = false,
    val image: String? = "",
    val shipId: String? = "",
    val shipModel: String? = "",
    val shipName: String? = "",
    val shipType: String? = "",
    val status: String? = "",
    val url: String? = "",
    val weightLbs: Int? = 0,
    val yearBuilt: Int? = 0
)