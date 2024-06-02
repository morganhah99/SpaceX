package com.example.spacex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.rocket.GetRocketsUseCase
import com.example.spacex.converter.RocketListConverter
import com.example.spacex.model.RocketListModel
import com.example.spacex.ui.compose.list.rocket.RocketListAction
import com.example.spacex.ui.compose.list.rocket.RocketListSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val useCase: GetRocketsUseCase,
    private val converter: RocketListConverter
) : MviViewModel<RocketListModel, UiState<RocketListModel>, RocketListAction, RocketListSingleEvent>(){


    override fun initState(): UiState<RocketListModel> = UiState.Loading


    override fun handleAction(action: RocketListAction) {
        when (action) {
            is RocketListAction.Load -> {
                loadRockets()
            }
            is RocketListAction.OnRocketItemClick -> TODO("Not yet implemented")

        }
    }

    private fun loadRockets() {
        viewModelScope.launch {
            useCase.execute(GetRocketsUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect{
                    submitState(it)
                }
        }
    }


}