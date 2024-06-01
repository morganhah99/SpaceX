package com.example.spacex.ui.compose.list.history

import com.example.common.state.UiSingleEvent

sealed class HistoryListSingleEvent: UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String): HistoryListSingleEvent()
}