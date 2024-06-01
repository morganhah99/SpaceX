package com.example.data.repo

import kotlinx.coroutines.flow.Flow
import com.example.domain.entity.Capsule


interface RemoteCapsuleDataSource {

    fun getCapsules(): Flow<List<Capsule?>?>

}