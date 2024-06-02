package com.example.domain.repo

import com.example.domain.entity.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {

    fun getLaunches(): Flow<List<Launch>>

    fun getLaunchItem(number: Int?): Flow<Launch>
}