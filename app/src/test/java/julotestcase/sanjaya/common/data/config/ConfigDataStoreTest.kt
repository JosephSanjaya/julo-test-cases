package julotestcase.sanjaya.common.data.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
class ConfigDataStoreTest {

    @Mock
    lateinit var config: FirebaseRemoteConfig

    @InjectMocks
    lateinit var configDataStore: ConfigDataStore

    @Test
    fun `fetchAndActivate should emit true when successful`() = runTest {
        // given
        val expectedResult = true
        `when`(config.fetchAndActivate().await()).thenReturn(expectedResult)

        // when
        val result = configDataStore.fetchAndActivate().first()

        // then
        assertEquals(expectedResult, result)
    }

    @Test
    fun `fetchAndActivate should emit false when unsuccessful`() = runTest {
        // given
        val expectedResult = false
        `when`(config.fetchAndActivate().await()).thenReturn(expectedResult)

        // when
        val result = configDataStore.fetchAndActivate().first()

        // then
        assertEquals(expectedResult, result)
    }
}

