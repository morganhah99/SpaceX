package com.example.spacex.model

import com.example.spacex.converter.HistoryListConverter


data class HistoryListModel(
    val items: List<History> = listOf()
)

data class History(
    val details: String? = "",
    val eventDateUtc: String? = "",
    val flightNumber: Int? = 0,
    val id: Int? = 0,
    val title: String? = ""
)