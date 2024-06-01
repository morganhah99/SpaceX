package com.example.domain.usecase.history

import com.example.domain.entity.History
import com.example.domain.repo.HistoryRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHistoryByIdUseCase(
    configuration: Configuration,
    private val repo: HistoryRepository
): UseCase<GetHistoryByIdUseCase.Request, GetHistoryByIdUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getHistoryItem(request.id)
            .map {
                Response(it)
            }





    data class Request(val id: Int?) : UseCase.Request
    data class Response(val historyItem: History?) : UseCase.Response
}