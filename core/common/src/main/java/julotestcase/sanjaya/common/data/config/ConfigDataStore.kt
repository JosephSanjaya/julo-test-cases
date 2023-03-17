package julotestcase.sanjaya.common.data.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

/**
 * Implementation of [ConfigRepo] that fetches and activates Firebase remote config data.
 */
class ConfigDataStore @Inject constructor(
    private val config: FirebaseRemoteConfig
) : ConfigRepo {
    /**
     * Private boolean variable isFetchedAndActivated to keep track of whether
     * the Firebase Remote Config has been fetched and activated.
     */
    private var isFetchedAndActivated = false

    /**
     * A private function that creates a Flow emitting a single value of type T. The function
     * will fetch and activate the Firebase Remote Config if it hasn't been fetched and activated
     * yet, and then emit the result of the provided block.
     *
     * @param T the type of the value to be emitted.
     * @param block a lambda function that returns the value to be emitted by the Flow.
     * @return a Flow that emits a single value of type T.
     */
    private inline fun <reified T> flowBuilder(
        crossinline block: () -> T
    ) = flow {
        // If the Firebase Remote Config hasn't been fetched and activated yet, fetch and activate it.
        if (!isFetchedAndActivated) {
            config.fetchAndActivate().await()
            isFetchedAndActivated = true
        }
        // Emit the result of the provided block.
        emit(block())
    }

    override fun fetch(): Flow<Boolean> = callbackFlow {
        config.fetchAndActivate().addOnCompleteListener {
            trySend(it.isSuccessful)
            close(it.exception)
        }
    }

    override fun getString(key: String): Flow<String> = flowBuilder { config.getString(key) }

    override fun getLong(key: String): Flow<Long> = flowBuilder { config.getLong(key) }

    override fun getInt(key: String): Flow<Int> = flowBuilder { config.getDouble(key).toInt() }

    override fun getDouble(key: String): Flow<Double> = flowBuilder { config.getDouble(key) }
}
