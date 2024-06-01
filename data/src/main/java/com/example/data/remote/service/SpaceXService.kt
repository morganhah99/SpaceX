package com.example.data.remote.service

import com.example.data.remote.model.CapsuleItemModel
import retrofit2.http.GET

interface SpaceXService {

    @GET("capsules")
    suspend fun getCapsules(): List<CapsuleItemModel>

    @GET("capsules/{capsule_serial}")
    suspend fun getCapsule(serial: String?): CapsuleItemModel


}