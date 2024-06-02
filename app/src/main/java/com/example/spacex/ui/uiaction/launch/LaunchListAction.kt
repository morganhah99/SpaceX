package com.example.spacex.ui.uiaction.launch

import com.example.common.state.UiAction

sealed class LaunchListAction: UiAction {

    data object Load: LaunchListAction()

    data class OnLaunchItemClick(
        val number: Int?
    ) : LaunchListAction()

}