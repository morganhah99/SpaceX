package com.example.data.remote.source.launch

import com.example.data.remote.model.launch.LaunchItemModel
import com.example.data.remote.model.mission.MissionItemModel
import com.example.data.remote.service.SpaceXService
import com.example.data.remote.repo.launch.RemoteLaunchDataSource
import com.example.domain.entity.Launch
import com.example.domain.entity.Mission
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteLaunchDataSourceImpl @Inject constructor(
    private val service: SpaceXService
): RemoteLaunchDataSource {

    override fun getLaunches(): Flow<List<Launch>> = flow {
        val launches = service.getLaunches()
        emit(launches)
    }.map { launchList ->
        launchList.map { launch -> convert(launch) }
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    override fun getLaunch(number: Int?): Flow<Launch> = flow {
        emit(service.getLaunch(number))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }


    private fun convert(model: LaunchItemModel): Launch {
        return Launch(
            details = model.details,
            flightNumber = model.flightNumber,
            launchSuccess = model.launchSuccess,
            launchYear = model.launchYear,
            missionName = model.missionName
        )
    }
}