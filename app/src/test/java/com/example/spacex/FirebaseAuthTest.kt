package com.example.spacex

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.example.common.nav.NavRoutes
import com.example.spacex.ui.compose.auth.checkAuthStatus
import com.example.spacex.ui.compose.auth.loginWithEmailAndPassword
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class FirebaseAuthTest {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavHostController
    private lateinit var context: Context

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        auth = mockk(relaxed = true)
        navController = mockk(relaxed = true)
        context = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `after successful login with email and password, should navigate to home`() =
        runBlockingTest {
            val email = "test@example.com"
            val password = "password"
            val task = mockk<Task<AuthResult>>(relaxed = true)
            every { task.isSuccessful } returns true

            every {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(any())
            } answers {
                firstArg<OnCompleteListener<AuthResult>>().onComplete(task)
                task
            }

            loginWithEmailAndPassword(email, password, auth, context, navController)

            verify { navController.navigate(NavRoutes.Home.route) }
        }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `test checkAuthStatus authenticated`() = runBlockingTest {
//        val user = mockk<FirebaseUser>()
//        every { auth.currentUser } returns user
//
//        checkAuthStatus(auth, navController)
//        val slot = slot<NavOptionsBuilder.() -> Unit>()
//
//
//        verify { navController.navigate(eq(NavRoutes.Home.route), capture(slot)) }
//
//        val navOptionsBuilderMock = mockk<NavOptionsBuilder>(relaxed = true)
//        slot.captured.invoke(navOptionsBuilderMock)
//
//        verify {
//            navOptionsBuilderMock.popUpTo(NavRoutes.Home.route, withArg {
//                it.inclusive = true
//            })
//        }
//    }
}
