package com.example.spacex.model

import com.example.spacex.converter.HistoryListConverter


data class HistoryListModel(
    val items: List<HistoryItem> = listOf()
)

data class HistoryItem(
    val details: String? = "",
    val eventDateUnix: Int? = 0,
    val eventDateUtc: String? = "",
    val flightNumber: Int? = 0,
    val id: Int? = 0,
    val title: String? = ""
)