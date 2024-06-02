package com.example.spacex.ui.uiaction.launch

import com.example.common.state.UiAction

sealed class LaunchListAction: UiAction {

    data object Load: LaunchListAction()

    data class OnLaunchItemClick(
        val details: String?,
        val success: Boolean?,
        val missionName: String?,
        val launchYear: String?
    ) : LaunchListAction()

}