package com.example.domain.usecase.rocket

import com.example.domain.entity.Rocket
import com.example.domain.repo.RocketRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRocketByIdUseCase(
    configuration: Configuration,
    private val repo: RocketRepository
) : UseCase<GetRocketByIdUseCase.Request, GetRocketByIdUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getRocket(request.id)
            .map {
                Response(it)
            }


    data class Request(val id: Int?) : UseCase.Request
    data class Response(val rocket: Rocket?) : UseCase.Response
}