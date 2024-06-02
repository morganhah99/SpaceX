package com.example.data.remote.service

import com.example.data.remote.model.launch.LaunchItemModel
import com.example.data.remote.model.capsule.CapsuleItemModel
import com.example.data.remote.model.history.HistoryModel
import com.example.data.remote.model.mission.MissionItemModel
import com.example.data.remote.model.rocket.RocketItemModel
import com.example.data.remote.model.ship.ShipItemModel
import retrofit2.http.GET

interface SpaceXService {

    @GET("capsules")
    suspend fun getCapsules(): List<CapsuleItemModel>

    @GET("capsules/{capsule_serial}")
    suspend fun getCapsule(serial: String?): CapsuleItemModel

    @GET("history")
    suspend fun getHistory(): List<HistoryModel>

    @GET("history/{id}")
    suspend fun getHistoryItem(id: Int?): HistoryModel

    @GET("missions")
    suspend fun getMissions(): List<MissionItemModel>

    @GET("missions/{mission_id}")
    suspend fun getMission(id: String?): MissionItemModel

    @GET("rockets")
    suspend fun getRockets(): List<RocketItemModel>

    @GET("rockets/{id}")
    suspend fun getRocket(id: Int?): RocketItemModel

    @GET("launches")
    suspend fun getLaunches(): List<LaunchItemModel>

    @GET("launches/{flight_number}")
    suspend fun getLaunch(number: Int?): LaunchItemModel

    @GET("ships")
    suspend fun getShips(): List<ShipItemModel>

    @GET("ships/{ship_id}")
    suspend fun getShip(id: String?): ShipItemModel


}