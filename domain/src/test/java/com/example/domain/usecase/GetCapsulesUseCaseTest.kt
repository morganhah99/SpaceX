package com.example.domain.usecase

import com.example.domain.entity.Capsule
import com.example.domain.repo.CapsuleRepository
import com.example.domain.usecase.capsule.GetCapsulesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCapsulesUseCaseTest {

    private lateinit var getCapsulesUseCase: GetCapsulesUseCase
    private val capsuleRepository: CapsuleRepository = mockk()
    private val configuration: UseCase.Configuration = mockk()

    @Before
    fun setUp() {
        getCapsulesUseCase = GetCapsulesUseCase(configuration, capsuleRepository)
    }

    @Test
    fun `process should return a list of capsules when repository returns data`(): Unit = runBlocking {

        val expectedCapsules = listOf(Capsule("1", "Capsule 1"), Capsule("2", "Capsule 2"))
        coEvery { capsuleRepository.getCapsules() } returns flowOf(expectedCapsules)

        val result = getCapsulesUseCase.process(GetCapsulesUseCase.Request).first()

        assertEquals(GetCapsulesUseCase.Response(expectedCapsules), result)
    }

    @Test
    fun `process should return empty list when repository returns no data`() = runBlocking {
        // Given
        coEvery { capsuleRepository.getCapsules() } returns flowOf(emptyList())

        // When
        val result = getCapsulesUseCase.process(GetCapsulesUseCase.Request).first()

        // Then
        assertEquals(GetCapsulesUseCase.Response(emptyList()), result)
    }

//    @Test
//    fun `process should handle error from repository`() = runBlocking {
//
//        val exception = RuntimeException("Something went wrong")
//
//        coEvery { capsuleRepository.getCapsules() } returns flow { throw exception }
//
//        val result = getCapsulesUseCase.execute(GetCapsulesUseCase.Request).first()
//
//        assert(result is Error(UseCaseException.createFromThrowable(it)))
//    }


}
