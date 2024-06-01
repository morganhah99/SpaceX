package com.example.data.remote.service

import com.example.data.remote.model.Capsule
import retrofit2.http.GET

interface SpaceXService {

    @GET("capsules")
    suspend fun getCapsuleModel(): Capsule

}