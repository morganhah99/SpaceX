package com.example.data.remote.repo.history

import com.example.domain.entity.History
import kotlinx.coroutines.flow.Flow

interface RemoteHistoryDataSource {

    fun getHistory(): Flow<List<History?>?>

    fun getHistoryItem(id: Int?): Flow<History>
}