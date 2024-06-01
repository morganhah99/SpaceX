package com.example.domain.repo

import com.example.domain.entity.Mission
import kotlinx.coroutines.flow.Flow

interface MissionRepository {

    fun getMissions(): Flow<List<Mission>>

    fun getMission(id: String?): Flow<Mission>
}