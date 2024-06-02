package com.example.data.remote.source.history

import com.example.data.remote.model.history.HistoryModel
import com.example.data.remote.service.SpaceXService
import com.example.data.remote.repo.history.RemoteHistoryDataSource
import com.example.domain.entity.History
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteHistoryDataSourceImpl @Inject constructor(
    private val service: SpaceXService
): RemoteHistoryDataSource {
    override fun getHistory(): Flow<List<History?>?> = flow {
        val history = service.getHistory()
        emit(history)
    }.map { historyList ->
        historyList.map { history -> convert(history) }
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    override fun getHistoryItem(id: Int?): Flow<History> = flow {
        emit(service.getHistoryItem(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    private fun convert(model: HistoryModel): History {
        return History(
            details = model.details,
            eventDateUnix = model.eventDateUnix,
            flightNumber = model.flightNumber,
            id = model.id,
            title = model.title
        )
    }


}


