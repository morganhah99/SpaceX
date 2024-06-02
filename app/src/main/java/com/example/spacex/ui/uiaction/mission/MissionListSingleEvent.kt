package com.example.spacex.ui.uiaction.mission

import com.example.common.state.UiSingleEvent

sealed class MissionListSingleEvent: UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String): MissionListSingleEvent()
}