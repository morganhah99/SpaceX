package com.example.domain.repo

import com.example.domain.entity.Capsule
import kotlinx.coroutines.flow.Flow

interface CapsuleRepository {

    fun getCapsules(): Flow<List<Capsule?>?>

    fun getCapsule(capsuleSerial: String?): Flow<Capsule>
}