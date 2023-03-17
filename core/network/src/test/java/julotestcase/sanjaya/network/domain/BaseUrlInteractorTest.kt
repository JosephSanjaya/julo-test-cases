package julotestcase.sanjaya.network.domain


import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import julotestcase.sanjaya.common.data.config.ConfigRepo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class BaseUrlInteractorTest {

    // Create a MockK mock for the ConfigRepo interface
    private val mockRepo = mockk<ConfigRepo>()

    // Create an instance of the ApiTokenConfigInteractor class using the mock repository
    private val useCase: BaseUrlConfigUseCase = BaseUrlInteractor(mockRepo)

    @Test
    fun `getBaseUrl returns expected value`() = runTest {

        // Mock the behavior of the ConfigRepo getString() function to return a flow with a specific value
        coEvery { mockRepo.getString(BaseUrlInteractor.BASE_URL) } returns flowOf("https://test")

        // Call the getApiToken() function and collect the result
        val result = useCase.getBaseUrl().first()

        // Verify that the ConfigRepo getString() function was called with the correct key
        coVerify { mockRepo.getString(BaseUrlInteractor.BASE_URL) }

        // Verify that the result is equal to the expected value
        assertEquals("https://test", result)
    }
}
