package com.example.domain.usecase

import com.example.domain.entity.Capsule
import com.example.domain.repo.CapsuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCapsulesUseCase(
    configuration: Configuration,
    private val repo: CapsuleRepository
) : UseCase<GetCapsulesUseCase.Request, GetCapsulesUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getCapsules()
            .map {
                Response(it)
            }

    data object Request : UseCase.Request
    data class Response(val capsules: List<Capsule?>?) : UseCase.Response
}