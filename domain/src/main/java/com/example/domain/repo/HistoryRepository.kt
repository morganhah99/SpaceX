package com.example.domain.repo

import com.example.domain.entity.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistory(): Flow<List<History>>

    fun getHistoryItem(id: Int?): Flow<History>
}