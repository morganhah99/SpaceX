package com.example.data.remote.source.rocket

import com.example.data.remote.model.rocket.RocketItemModel
import com.example.data.remote.service.SpaceXService
import com.example.data.remote.repo.rocket.RemoteRocketDataSource
import com.example.domain.entity.Rocket
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRocketDataSourceImpl @Inject constructor(
    private val service: SpaceXService
) : RemoteRocketDataSource {


    override fun getRockets(): Flow<List<Rocket>> = flow {
        val rockets = service.getRockets()
        emit(rockets)
    }.map { rocketList ->
        rocketList.map { rocket -> convert(rocket) }
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    override fun getRocket(id: Int?): Flow<Rocket> = flow {
        emit(service.getRocket(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    private fun convert(model: RocketItemModel): Rocket {
        return Rocket(
            description = model.description,
            id = model.id,
            rocketName = model.rocketName,
            company = model.company,
            costPerLaunch = model.costPerLaunch
        )
    }
}