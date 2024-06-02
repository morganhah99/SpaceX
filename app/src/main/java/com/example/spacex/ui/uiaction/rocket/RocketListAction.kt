package com.example.spacex.ui.uiaction.rocket

import com.example.common.state.UiAction

sealed class RocketListAction : UiAction {

    data object Load : RocketListAction()

    data class OnRocketItemClick(
        val id: Int?
    ) : RocketListAction()
}