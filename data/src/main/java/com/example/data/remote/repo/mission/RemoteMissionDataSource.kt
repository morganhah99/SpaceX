package com.example.data.remote.repo.mission

import com.example.domain.entity.Mission
import kotlinx.coroutines.flow.Flow

interface RemoteMissionDataSource {

    fun getMissions(): Flow<List<Mission>>

    fun getMission(id: String?): Flow<Mission>
}