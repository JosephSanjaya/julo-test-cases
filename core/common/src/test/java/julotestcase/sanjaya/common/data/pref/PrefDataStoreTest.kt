package julotestcase.sanjaya.common.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class PrefDataStoreTest {

    @MockK
    lateinit var dataStore: DataStore<Preferences>

    lateinit var prefDataStore: PrefDataStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        prefDataStore = PrefDataStore(dataStore)
    }

    @Test
    fun `setData should set the correct value for a string key`() = runTest {
        // Given
        val key = "string_key"
        val value = "hello world"
        val expectedKey = stringPreferencesKey(key)
        prefDataStore.setData(key, value)

        // Then
        coVerify {
            dataStore.edit {
                it[expectedKey] = value
            }
        }
    }

    @Test
    fun `setData should set the correct value for an integer key`() = runTest {
        // Given
        val key = "integer_key"
        val value = 42
        val expectedKey = intPreferencesKey(key)

        // When
        prefDataStore.setData(key, value)

        // Then
        coVerify {
            dataStore.edit {
                it[expectedKey] = value
            }
        }
    }

    @Test
    fun `setData should set the correct value for a long key`() = runTest {
        // Given
        val key = "long_key"
        val value = 123456789L
        val expectedKey = longPreferencesKey(key)

        // When
        prefDataStore.setData(key, value)

        // Then
        coVerify {
            dataStore.edit {
                it[expectedKey] = value
            }
        }
    }

    @Test
    fun `setData should set the correct value for a double key`() = runTest {
        // Given
        val key = "double_key"
        val value = 3.14
        val expectedKey = doublePreferencesKey(key)

        // When
        prefDataStore.setData(key, value)

        // Then
        coVerify {
            dataStore.edit {
                it[expectedKey] = value
            }
        }
    }

    @Test
    fun `setData should set the correct value for a float key`() = runTest {
        // Given
        val key = "float_key"
        val value = 1.23f
        val expectedKey = floatPreferencesKey(key)

        // When
        prefDataStore.setData(key, value)

        // Then
        coVerify {
            dataStore.edit {
                it[expectedKey] = value
            }
        }
    }
}