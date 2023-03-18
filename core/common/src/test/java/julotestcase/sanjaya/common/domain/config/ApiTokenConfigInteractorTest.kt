package julotestcase.sanjaya.common.domain.config

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class ApiTokenConfigInteractorTest {
//
//    // Create a MockK mock for the ConfigRepo interface
//    private val mockRepo = mockk<ConfigRepo>()
//
//    // Create an instance of the ApiTokenConfigInteractor class using the mock repository
//    private val useCase: ApiTokenConfigUseCase = ApiTokenConfigInteractor(mockRepo)
//
//    @Test
//    fun `getApiToken returns expected value`() = runTest {
//
//        // Mock the behavior of the ConfigRepo getString() function to return a flow with a specific value
//        coEvery { mockRepo.getString(ApiTokenConfigInteractor.OPEN_WEATHER_API_TOKEN_KEY) } returns flowOf("test_token")
//
//        // Call the getApiToken() function and collect the result
//        val result = useCase.getOpenWeatherApiKey().first()
//
//        // Verify that the ConfigRepo getString() function was called with the correct key
//        coVerify { mockRepo.getString(ApiTokenConfigInteractor.OPEN_WEATHER_API_TOKEN_KEY) }
//
//        // Verify that the result is equal to the expected value
//        assertEquals("test_token", result)
//    }
}
