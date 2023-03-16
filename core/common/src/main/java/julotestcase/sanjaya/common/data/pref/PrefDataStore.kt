package julotestcase.sanjaya.common.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

import javax.inject.Inject
import javax.inject.Named

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A [PrefRepo] implementation that uses [DataStore] to store key-value pairs.
 *
 * @param dataStore The [DataStore] instance to use for storing the data.
 */
class PrefDataStore @Inject constructor(
    @Named(PrefRepo.PREF_NAME) private val dataStore: DataStore<Preferences>
) : PrefRepo {
    /**
     * Sets the value for the given key in the data store.
     *
     * @param key The key to set the value for.
     * @param value The value to set for the key.
     * @throws UnsupportedOperationException if the value type is not supported.
     */
    override suspend fun setData(key: String, value: Any) {
        dataStore.edit {
            when (value) {
                is String -> {
                    val prefKey = stringPreferencesKey(key)
                    it[prefKey] = value
                }
                is Int -> {
                    val prefKey = intPreferencesKey(key)
                    it[prefKey] = value
                }
                is Long -> {
                    val prefKey = longPreferencesKey(key)
                    it[prefKey] = value
                }
                is Double -> {
                    val prefKey = doublePreferencesKey(key)
                    it[prefKey] = value
                }
                is Float -> {
                    val prefKey = floatPreferencesKey(key)
                    it[prefKey] = value
                }
                else -> throw UnsupportedOperationException()
            }
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    /**
     * Gets the value for the given key as a [Flow] of [String].
     *
     * @param key The key to get the value for.
     * @return A [Flow] that emits the value for the key.
     */
    override fun getString(key: String): Flow<String> = dataStore.data.map {
        it[stringPreferencesKey(key)].orEmpty()
    }

    /**
     * Gets the value for the given key as a [Flow] of [Long].
     *
     * @param key The key to get the value for.
     * @return A [Flow] that emits the value for the key.
     */
    override fun getLong(key: String): Flow<Long> = dataStore.data.map {
        it[longPreferencesKey(key)] ?: 0L
    }

    /**
     * Gets the value for the given key as a [Flow] of [Int].
     *
     * @param key The key to get the value for.
     * @return A [Flow] that emits the value for the key.
     */
    override fun getInt(key: String): Flow<Int> = dataStore.data.map {
        it[intPreferencesKey(key)] ?: 0
    }

    /**
     * Gets the value for the given key as a [Flow] of [Double].
     *
     * @param key The key to get the value for.
     * @return A [Flow] that emits the value for the key.
     */
    override fun getDouble(key: String): Flow<Double> = dataStore.data.map {
        it[doublePreferencesKey(key)] ?: 0.0
    }
}
