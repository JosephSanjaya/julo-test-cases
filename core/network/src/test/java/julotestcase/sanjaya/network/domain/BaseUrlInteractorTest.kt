package julotestcase.sanjaya.network.domain


import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class BaseUrlInteractorTest {
//
//    // Create a MockK mock for the ConfigRepo interface
//    private val mockRepo = mockk<ConfigRepo>()
//
//    // Create an instance of the ApiTokenConfigInteractor class using the mock repository
//    private val useCase: BaseUrlConfigUseCase = BaseUrlInteractor(mockRepo)
//
//    @Test
//    fun `getBaseUrl returns expected value`() = runTest {
//
//        // Mock the behavior of the ConfigRepo getString() function to return a flow with a specific value
//        coEvery { mockRepo.getString(BaseUrlInteractor.OPEN_WEATHER_BASE_URL) } returns flowOf("https://test")
//
//        // Call the getApiToken() function and collect the result
//        val result = useCase.getOpenWeatherBaseUrl().first()
//
//        // Verify that the ConfigRepo getString() function was called with the correct key
//        coVerify { mockRepo.getString(BaseUrlInteractor.OPEN_WEATHER_BASE_URL) }
//
//        // Verify that the result is equal to the expected value
//        assertEquals("https://test", result)
//    }
}
