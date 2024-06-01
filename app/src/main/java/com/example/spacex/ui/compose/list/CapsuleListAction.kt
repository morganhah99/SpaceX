package com.example.spacex.ui.compose.list

import com.example.common.state.UiAction

sealed class CapsuleListAction : UiAction {

    data object Load : CapsuleListAction()
    data class OnCapsuleItemClick(
        val serial: String?
    ) : CapsuleListAction()
}