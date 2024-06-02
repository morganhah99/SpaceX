package com.example.data.remote.repo.capsule

import kotlinx.coroutines.flow.Flow
import com.example.domain.entity.Capsule


interface RemoteCapsuleDataSource {

    fun getCapsules(): Flow<List<Capsule?>?>

    fun getCapsule(serial: String?): Flow<Capsule>

}