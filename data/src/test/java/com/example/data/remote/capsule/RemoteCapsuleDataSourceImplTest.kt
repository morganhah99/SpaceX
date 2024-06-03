package com.example.data.remote.capsule

import com.example.data.remote.model.capsule.CapsuleItemModel
import com.example.data.remote.service.SpaceXService
import com.example.data.remote.source.capsule.RemoteCapsuleDataSourceImpl
import com.example.domain.entity.Capsule
import com.example.domain.entity.UseCaseException
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class RemoteCapsuleDataSourceImplTest {

    private lateinit var remoteCapsuleDataSource: RemoteCapsuleDataSourceImpl

    private val service: SpaceXService = mockk()

    @Before
    fun setUp() {
        remoteCapsuleDataSource = RemoteCapsuleDataSourceImpl(service)
    }

    @Test
    fun `getCapsules should convert the list of CapsuleItemModel to a list of Capsule entity`() =
        runBlocking {

            val capsuleItemModelList = listOf(
                CapsuleItemModel(capsuleId = "1", capsuleSerial = "C123"),
                CapsuleItemModel(capsuleId = "2", capsuleSerial = "C124")
            )

            val expectedCapsuleEntityList = listOf(
                Capsule(capsuleId = "1", capsuleSerial = "C123"),
                Capsule(capsuleId = "2", capsuleSerial = "C124")
            )

            coEvery { service.getCapsules() } returns capsuleItemModelList

            val result = remoteCapsuleDataSource.getCapsules().first()

            assertEquals(expectedCapsuleEntityList, result)
        }

    @Test
    fun `getCapsule should convert the given CapsuleItemModel to a Capsule entity`() = runBlocking {

        val capsuleItemModel = CapsuleItemModel(capsuleId = "1", capsuleSerial = "C123")

        val expectedCapsuleEntity = Capsule(capsuleId = "1", capsuleSerial = "C123")

        val serial = "C123"

        coEvery { service.getCapsule(serial) } returns capsuleItemModel

        val result = remoteCapsuleDataSource.getCapsule(serial).first()

        assertEquals(expectedCapsuleEntity, result)
    }

    @Test
    fun `getCapsules should throw an exception when api call fails` (): Unit = runBlocking {
        val exception = RuntimeException("Data source error")
        coEvery { service.getCapsules() } throws exception
        assertThrows<UseCaseException.SpaceXException> {
            remoteCapsuleDataSource.getCapsules().first()
        }
    }

    @Test
    fun `getCapsule should throw an exception when api call fails` (): Unit = runBlocking {
        val serial = "C123"
        val exception = RuntimeException("Data source error")
        coEvery { service.getCapsule(serial) } throws exception

        assertThrows<UseCaseException.SpaceXException> {
            remoteCapsuleDataSource.getCapsule(serial).first()
        }
    }


}