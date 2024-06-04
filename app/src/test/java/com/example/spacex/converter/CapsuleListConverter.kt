package com.example.spacex.converter

import android.content.Context
import com.example.domain.entity.Capsule
import com.example.domain.usecase.capsule.GetCapsulesUseCase
import com.example.spacex.model.CapsuleListModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

//class CapsuleListConverterTest {
//
//    private lateinit var context: Context
//    private lateinit var converter: CapsuleListConverter
//
//    @Before
//    fun setUp() {
//        context = mockk()
//        converter = CapsuleListConverter(context)
//    }
//
//    @Test
//    fun `convertSuccess should correctly map GetCapsulesUseCase Response to CapsuleListModel`() {
//        val response = GetCapsulesUseCase.Response(
//            capsules = listOf(
//                Capsule(
//                    capsuleSerial = "C101",
//                    details = "Test details",
//                    landings = 3,
//                    originalLaunch = "2020-12-06",
//                    status = "active",
//                    type = "Dragon 1.0"
//                ),
//                Capsule(
//                    capsuleSerial = "C102",
//                    details = "Test details 2",
//                    landings = 1,
//                    originalLaunch = "2021-01-10",
//                    status = "retired",
//                    type = "Dragon 2.0"
//                )
//            )
//        )
//
//        val expected = CapsuleListModel(
//            items = listOf(
//                Capsule(
//                    capsuleSerial = "C101",
//                    details = "Test details",
//                    landings = 3,
//                    originalLaunch = "2020-12-06",
//                    status = "active",
//                    type = "Dragon 1.0"
//                ),
//                Capsule(
//                    capsuleSerial = "C102",
//                    details = "Test details 2",
//                    landings = 1,
//                    originalLaunch = "2021-01-10",
//                    status = "retired",
//                    type = "Dragon 2.0"
//                )
//            )
//        )
//
//        val actual = converter.convertSuccess(response)
//
//        assertEquals(expected, actual)
//    }
//}