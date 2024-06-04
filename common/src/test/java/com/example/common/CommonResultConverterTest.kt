package com.example.common

import com.example.common.state.CommonResultConverter
import com.example.common.state.UiState
import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.example.domain.entity.Result
import com.example.domain.entity.UseCaseException

//class CommonResultConverterTest {
//
//
//
//    inner class TestResultConverter : CommonResultConverter<String, Int>() {
//        override fun convertSuccess(data: String): Int {
//            return data.length
//        }
//    }
//
//    private val converter = TestResultConverter()
//
//
//    @Test
//    fun `convert should return UiState_Error when Result_Error`() {
//
//        val errorMessage = "Test error"
//        val result = Result.Error(UseCaseException(errorMessage))
//
//        val expectedValue = UiState.Error(errorMessage)
//
//        val uiState = converter.convert(result)
//
//        assert(uiState is UiState.Error)
//        assertEquals(errorMessage, (uiState as UiState.Error))
//    }
//}