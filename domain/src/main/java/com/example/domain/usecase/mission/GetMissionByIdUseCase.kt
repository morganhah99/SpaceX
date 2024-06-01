package com.example.domain.usecase.mission

import com.example.domain.entity.Mission
import com.example.domain.repo.MissionRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMissionByIdUseCase(
    configuration: Configuration,
    private val repo: MissionRepository
): UseCase<GetMissionByIdUseCase.Request, GetMissionByIdUseCase.Response>(configuration) {


    override fun process(request: Request): Flow<Response> =
        repo.getMission(request.id)
            .map {
                Response(it)
            }

    data class Request(val id: String?) : UseCase.Request
    data class Response(val mission: Mission?) : UseCase.Response


}