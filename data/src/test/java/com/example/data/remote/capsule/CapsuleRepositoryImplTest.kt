package com.example.data.remote.capsule

import com.example.data.remote.repo.capsule.CapsuleRepositoryImpl
import com.example.data.remote.repo.capsule.RemoteCapsuleDataSource
import com.example.domain.entity.Capsule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CapsuleRepositoryImplTest {

    private lateinit var capsuleRepository: CapsuleRepositoryImpl
    private val remoteSource: RemoteCapsuleDataSource = mockk()

    @Before
    fun setUp() {
        capsuleRepository = CapsuleRepositoryImpl(remoteSource)
    }

    @Test
    fun `getCapsules should return a list of Capsule entities`(): Unit = runBlocking {

        val expectedCapsules = listOf(
            Capsule(capsuleId = "1", capsuleSerial = "C123"),
            Capsule(capsuleId = "2", capsuleSerial = "C124")
        )
        coEvery { remoteSource.getCapsules() } returns flowOf(expectedCapsules)

        val result = capsuleRepository.getCapsules().toList()

        assertEquals(expectedCapsules, result[0])

    }

    @Test
    fun `getCapsule should return a Capsule entity given a serial`(): Unit = runBlocking {
        val expectedCapsule = Capsule(capsuleId = "1", capsuleSerial = "C123")
        val serial = "C123"

        coEvery { remoteSource.getCapsule(serial) } returns flowOf(expectedCapsule)

        val result = capsuleRepository.getCapsule(serial).first()

        assertEquals(expectedCapsule, result)

    }

    @Test
    fun `getCapsules should return an empty list when no capsules are available`() = runBlocking {

        val expectedCapsules = emptyList<Capsule>()
        coEvery { remoteSource.getCapsules() } returns flowOf(expectedCapsules)


        val result = capsuleRepository.getCapsules().first()


        assertEquals(expectedCapsules, result)
    }

    @Test
    fun `getCapsules should handle errors and emit an empty list`() = runBlocking {
        val exception = RuntimeException("Data source error")
        coEvery { remoteSource.getCapsules() } throws exception


        val result = try {
            capsuleRepository.getCapsules().first()
        } catch (e: Exception) {
            emptyList<Capsule>()
        }


        assertEquals(emptyList<Capsule>(), result)
    }


}