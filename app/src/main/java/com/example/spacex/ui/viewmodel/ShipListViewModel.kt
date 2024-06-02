package com.example.spacex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.ship.GetShipsUseCase
import com.example.spacex.converter.ShipListConverter
import com.example.spacex.model.HistoryListModel
import com.example.spacex.model.ShipListModel
import com.example.spacex.ui.uiaction.history.HistoryListAction
import com.example.spacex.ui.uiaction.ship.ShipListAction
import com.example.spacex.ui.uiaction.ship.ShipListSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShipListViewModel @Inject constructor(
    private val useCase: GetShipsUseCase,
    private val converter: ShipListConverter
) : MviViewModel<ShipListModel, UiState<ShipListModel>, ShipListAction, ShipListSingleEvent>(){

    override fun initState(): UiState<ShipListModel> = UiState.Loading


    override fun handleAction(action: ShipListAction) {
        when (action) {
            is ShipListAction.Load -> {
                loadShips()
            }
            is ShipListAction.OnShipItemClick ->  TODO()
        }
    }

    private fun loadShips(){
        viewModelScope.launch {
            useCase.execute(GetShipsUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect{
                    submitState(it)
                }
        }
    }
}