import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.common.nav.routes.CapsuleInput
import com.example.common.nav.routes.CapsuleNavRoutes
import com.example.common.state.UiState
import com.example.domain.entity.Capsule
import com.example.domain.entity.Result
import com.example.domain.usecase.capsule.GetCapsulesUseCase
import com.example.spacex.converter.CapsuleListConverter
import com.example.spacex.model.CapsuleListModel
import com.example.spacex.ui.uiaction.capsule.CapsuleListAction
import com.example.spacex.ui.uiaction.capsule.CapsuleListSingleEvent
import com.example.spacex.ui.viewmodel.CapsuleListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CapsuleListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CapsuleListViewModel

    private val useCase: GetCapsulesUseCase = mock(GetCapsulesUseCase::class.java)
    private val converter: CapsuleListConverter = mock(CapsuleListConverter::class.java)
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CapsuleListViewModel(useCase, converter)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testInitialStateIsLoading() {
        Assert.assertEquals(UiState.Loading, viewModel.uiStateFlow.value)
    }


//    @Test
//    fun testLoadAction() = runBlockingTest {
//        val capsuleList = listOf(
//            Capsule("C101", "Details 1", "2", 2, "Dragon 1.0", 1),
//            Capsule("C102", "Details 2", "1", 1, "Dragon 2.0", 1)
//        )
//        val response = GetCapsulesUseCase.Response(capsuleList)
//        val result = Result.Success(response)
//
//        val convertedCapsuleListModel = CapsuleListModel(capsuleList)
//        `when`(useCase.execute(GetCapsulesUseCase.Request)).thenReturn(flowOf(result))
//        `when`(converter.convert(result)).thenReturn(UiState.Success(convertedCapsuleListModel))
//
//        viewModel.handleAction(CapsuleListAction.Load)
//
//        verify(useCase).execute(GetCapsulesUseCase.Request)
//        verify(converter).convert(result)
//
//        Assert.assertEquals(UiState.Success(convertedCapsuleListModel), viewModel.uiStateFlow.value)
//    }

    fun <T> Flow<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
    ): T {
        var data: T? = null
        runBlocking {
            withTimeout(timeUnit.toMillis(time)) {
                data = this@getOrAwaitValue.first()
            }
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }
    @Test
    fun testOnCapsuleItemClickAction() = runBlockingTest {
        val action = CapsuleListAction.OnCapsuleItemClick(
            serial = "C101",
            details = "Some details",
            status = "active",
            landings = 3,
            type = "Dragon 1.0",
            launch = "Some launch"
        )

        viewModel.handleAction(action)

        val expectedEvent = CapsuleListSingleEvent.OpenDetailsScreen(
            CapsuleNavRoutes.Details.routeForCapsule(
                CapsuleInput(
                    serialId = action.serial,
                    details = action.details,
                    status = action.status,
                    landings = action.landings,
                    type = action.type,
                    launch = action.launch
                )
            )
        )

        Assert.assertEquals(expectedEvent, viewModel.singleEventFlow.getOrAwaitValue())
    }
}
