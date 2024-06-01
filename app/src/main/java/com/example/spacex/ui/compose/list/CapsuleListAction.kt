package com.example.spacex.ui.compose.list

import com.example.common.state.UiAction

sealed class CapsuleListAction : UiAction {

    data object Load : CapsuleListAction()
    data class OnMovieItemClick(
        val movieId: Int?,
        val title: String?,
        val backdropPath: String?,
        val overview: String?,
        val releaseDate: String?,
    ) : CapsuleListAction()
}