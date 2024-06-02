package com.example.data.remote.source.mission

import com.example.data.remote.model.mission.MissionItemModel
import com.example.data.remote.service.SpaceXService
import com.example.data.remote.repo.mission.RemoteMissionDataSource
import com.example.domain.entity.Mission
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteMissionDataSourceImpl @Inject constructor(
    private val service: SpaceXService
): RemoteMissionDataSource {

    override fun getMissions(): Flow<List<Mission>> = flow {
        val missions = service.getMissions()
        emit(missions)
    }.map { missionList ->
        missionList.map { mission -> convert(mission) }
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    override fun getMission(id: String?): Flow<Mission> = flow {
        emit(service.getMission(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    private fun convert(model: MissionItemModel): Mission {
        return Mission(
            description = model.description,
            missionId = model.missionId,
            missionName = model.missionName,
        )
    }
}