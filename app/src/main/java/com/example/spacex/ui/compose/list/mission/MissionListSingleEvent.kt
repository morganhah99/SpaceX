package com.example.spacex.ui.compose.list.mission

import com.example.common.state.UiSingleEvent

sealed class MissionListSingleEvent: UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String): MissionListSingleEvent()
}