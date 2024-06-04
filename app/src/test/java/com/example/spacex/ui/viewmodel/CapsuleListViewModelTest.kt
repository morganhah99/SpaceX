import com.example.common.nav.routes.CapsuleInput
import com.example.common.nav.routes.CapsuleNavRoutes
import com.example.common.state.UiState
import com.example.domain.usecase.capsule.GetCapsulesUseCase
import com.example.spacex.converter.CapsuleListConverter
import com.example.spacex.ui.uiaction.capsule.CapsuleListAction
import com.example.spacex.ui.uiaction.capsule.CapsuleListSingleEvent
import com.example.spacex.ui.viewmodel.CapsuleListViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CapsuleListViewModelTest {



    private lateinit var viewModel: CapsuleListViewModel
    private lateinit var useCase: GetCapsulesUseCase
    private lateinit var converter: CapsuleListConverter
    private val testDispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk()
        converter = mockk()
        viewModel = CapsuleListViewModel(useCase, converter)
    }



    @Test
    fun testInitialStateIsLoading() {
        Assert.assertEquals(UiState.Loading, viewModel.uiStateFlow.value)
    }

//    @Test
//    fun testLoadAction() = runBlockingTest {
//        val capsuleList = listOf(
//            Capsule("C101", "Details 1", "2", 2, "active", 1),
//            Capsule("C102", "Details 2", "1", 1, "retired", 1)
//        )
//        val response = GetCapsulesUseCase.Response(capsuleList)
//        val result = Result.Success(response)
//
//        val convertedCapsuleListModel = CapsuleListModel(
//            items = listOf(
//                Capsule("C101", "Details 1", 2, "2020-12-06", "active", "Dragon 1.0"),
//                Capsule("C102", "Details 2", 1, "2021-01-10", "retired", "Dragon 2.0")
//            )
//        )
//        coEvery { useCase.execute(GetCapsulesUseCase.Request) } returns flowOf(result)
//        coEvery { converter.convertSuccess(response) } returns convertedCapsuleListModel
//
//        viewModel.handleAction(CapsuleListAction.Load)
//
//        coVerify { useCase.execute(GetCapsulesUseCase.Request) }
//        coVerify { converter.convertSuccess(response) }
//
//        Assert.assertEquals(UiState.Success(convertedCapsuleListModel), viewModel.uiStateFlow.value)
//    }

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

        Assert.assertEquals(expectedEvent, viewModel.singleEventFlow.first())
    }
}