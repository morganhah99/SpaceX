package com.example.data.remote.source.capsule

import com.example.data.remote.model.capsule.CapsuleItemModel
import com.example.data.remote.service.SpaceXService
import com.example.data.remote.repo.capsule.RemoteCapsuleDataSource
import com.example.domain.entity.Capsule
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RemoteCapsuleDataSourceImpl @Inject constructor(
    private val service: SpaceXService
) : RemoteCapsuleDataSource {
    override fun getCapsules(): Flow<List<Capsule?>?> = flow {
        val capsules = service.getCapsules()
        emit(capsules)
    }.map { capsuleList ->
        capsuleList.map { capsule -> convert(capsule) }
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    override fun getCapsule(serial: String?): Flow<Capsule> = flow {
        emit(service.getCapsule(serial))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }


    private fun convert(model: CapsuleItemModel?): Capsule {
        return Capsule(
            capsuleId = model?.capsuleId,
            capsuleSerial = model?.capsuleSerial,
            details = model?.details,
            landings = model?.landings,
            originalLaunch = model?.originalLaunch,
            originalLaunchUnix = model?.originalLaunchUnix,
            reuseCount = model?.reuseCount,
            status = model?.status,
            type = model?.type
        )
    }
}