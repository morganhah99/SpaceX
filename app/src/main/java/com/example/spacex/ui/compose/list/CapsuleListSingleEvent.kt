package com.example.spacex.ui.compose.list

import com.example.common.state.UiSingleEvent

sealed class CapsuleListSingleEvent : UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String) : CapsuleListSingleEvent()
}