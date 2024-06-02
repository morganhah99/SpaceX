package com.example.data.remote.repo.launch

import com.example.domain.entity.Launch
import kotlinx.coroutines.flow.Flow

interface RemoteLaunchDataSource {

    fun getLaunches(): Flow<List<Launch>>

    fun getLaunch(number: Int?): Flow<Launch>
}