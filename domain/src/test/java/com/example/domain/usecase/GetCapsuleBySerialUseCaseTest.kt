package com.example.domain.usecase

import com.example.common.state.UiState
import com.example.domain.entity.Capsule
import com.example.domain.repo.CapsuleRepository
import com.example.domain.usecase.capsule.GetCapsuleBySerialUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Before
import org.junit.Test


class GetCapsuleBySerialUseCaseTest {

    private lateinit var getCapsuleBySerialUseCase: GetCapsuleBySerialUseCase
    private val capsuleRepository: CapsuleRepository = mockk()
    private val configuration: UseCase.Configuration = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { configuration.dispatcher } returns Dispatchers.Unconfined
        getCapsuleBySerialUseCase = GetCapsuleBySerialUseCase(configuration, capsuleRepository)
    }
    @Test
    fun `process should return capsule when repository returns data`() = runBlocking {

        val serial = "C1"
        val expectedCapsule = Capsule(serial, "Capsule 1")
        coEvery { capsuleRepository.getCapsule(serial) } returns flowOf(expectedCapsule)

        val result = getCapsuleBySerialUseCase.process(GetCapsuleBySerialUseCase.Request(serial)).first()

        assertEquals(GetCapsuleBySerialUseCase.Response(expectedCapsule), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `process should handle null serial input`() = runBlocking {

        val getCapsuleBySerialUseCase = GetCapsuleBySerialUseCase(configuration, capsuleRepository)

        val serial: String? = null
        coEvery { capsuleRepository.getCapsule(serial) } returns flowOf(null)

        val result = getCapsuleBySerialUseCase.process(GetCapsuleBySerialUseCase.Request(serial)).first()

        assertEquals(GetCapsuleBySerialUseCase.Response(null), result)

        Dispatchers.resetMain()
    }

    @Test
    fun `process should handle empty repository response`() = runBlocking {

        val serial = "C2"
        coEvery { capsuleRepository.getCapsule(serial) } returns flowOf(null)

        val result = getCapsuleBySerialUseCase.process(GetCapsuleBySerialUseCase.Request(serial)).first()

        assertEquals(GetCapsuleBySerialUseCase.Response(null), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `process should handle error from repository`() = runBlocking {

        val serial = "C3"
        val exception = RuntimeException("Something went wrong")
        coEvery { capsuleRepository.getCapsule(serial) } returns flow { throw exception }

        val result: UiState<GetCapsuleBySerialUseCase.Response> = try {
            val response = getCapsuleBySerialUseCase.process(GetCapsuleBySerialUseCase.Request(serial)).first()
            UiState.Success(response)
        } catch (e: Exception) {
            UiState.Error(e.message ?: "Unknown error")
        }

        assert(result is UiState.Error)
        if (result is UiState.Error) {
            assertEquals("Something went wrong", result.errorMessage)
        }

        Dispatchers.resetMain()
    }
}
