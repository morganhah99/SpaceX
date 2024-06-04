package com.example.common

import com.example.common.state.UiState
import junit.framework.TestCase.assertTrue
import org.junit.Test

class UiStateTest {

    @Test
    fun `test Loading state`() {
        val state: UiState<Nothing> = UiState.Loading
        assertTrue(state is UiState.Loading)
    }

}