package com.example.spacex.ui

import androidx.lifecycle.viewModelScope
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.GetCapsulesUseCase
import com.example.spacex.converter.CapsuleListConverter
import com.example.spacex.model.CapsuleListModel
import com.example.spacex.ui.compose.list.CapsuleListAction
import com.example.spacex.ui.compose.list.CapsuleListSingleEvent
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val useCase: GetCapsulesUseCase,
    private val converter: CapsuleListConverter
): MviViewModel<CapsuleListModel, UiState<CapsuleListModel>, CapsuleListAction, CapsuleListSingleEvent>() {
    override fun initState(): UiState<CapsuleListModel> = UiState.Loading


    override fun handleAction(action: CapsuleListAction) {
        TODO("Not yet implemented")
    }

    private fun loadCapsules() {
        viewModelScope.launch {
            useCase.execute(GetCapsulesUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }
}