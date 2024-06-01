package com.example.data.repo

import com.example.domain.entity.Capsule
import com.example.domain.repo.CapsuleRepository
import kotlinx.coroutines.flow.Flow

class CapsuleRepositoryImpl(
    private val remoteSource: RemoteCapsuleDataSource,
): CapsuleRepository {

    override fun getCapsules(): Flow<List<Capsule?>?> {
        return remoteSource.getCapsules()
    }
}