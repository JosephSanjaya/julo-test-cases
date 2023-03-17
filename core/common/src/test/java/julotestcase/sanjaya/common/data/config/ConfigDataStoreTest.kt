package julotestcase.sanjaya.common.data.config

import com.google.android.gms.tasks.Tasks
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConfigDataStoreTest {

    private val key = "test_key"
    private val value = "test_value"

    @MockK
    private lateinit var mockFirebaseRemoteConfig: FirebaseRemoteConfig

    private lateinit var configDataStore: ConfigRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        configDataStore = ConfigDataStore(mockFirebaseRemoteConfig)
    }

    @Test
    fun testGetString() = runBlocking {
        coEvery { mockFirebaseRemoteConfig.getString(key) } returns value
        coEvery { mockFirebaseRemoteConfig.fetchAndActivate() } returns Tasks.forResult(null)

        val flow = configDataStore.getString(key)
        assert(flow.first() == value)

        coVerify { mockFirebaseRemoteConfig.fetchAndActivate() }
    }

    @Test
    fun testGetLong() = runBlocking {
        val longValue = 123L
        coEvery { mockFirebaseRemoteConfig.getLong(key) } returns longValue
        coEvery { mockFirebaseRemoteConfig.fetchAndActivate() } returns Tasks.forResult(null)

        val flow = configDataStore.getLong(key)
        assert(flow.first() == longValue)

        coVerify { mockFirebaseRemoteConfig.fetchAndActivate() }
    }

    @Test
    fun testGetInt() = runBlocking {
        val doubleValue = 123.0
        coEvery { mockFirebaseRemoteConfig.getDouble(key) } returns doubleValue
        coEvery { mockFirebaseRemoteConfig.fetchAndActivate() } returns Tasks.forResult(null)

        val flow = configDataStore.getInt(key)
        assert(flow.first() == doubleValue.toInt())

        coVerify { mockFirebaseRemoteConfig.fetchAndActivate() }
    }

    @Test
    fun testGetDouble() = runBlocking {
        val doubleValue = 123.456
        coEvery { mockFirebaseRemoteConfig.getDouble(key) } returns doubleValue
        coEvery { mockFirebaseRemoteConfig.fetchAndActivate() } returns Tasks.forResult(null)

        val flow = configDataStore.getDouble(key)
        assert(flow.first() == doubleValue)

        coVerify { mockFirebaseRemoteConfig.fetchAndActivate() }
    }
}
