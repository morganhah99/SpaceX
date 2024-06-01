package com.example.spacex.ui.compose.list.mission

import com.example.common.state.UiAction

sealed class MissionListAction: UiAction {

    data object Load: MissionListAction()

    data class OnMissionItemClick(
        val id: String?
    ): MissionListAction()
}