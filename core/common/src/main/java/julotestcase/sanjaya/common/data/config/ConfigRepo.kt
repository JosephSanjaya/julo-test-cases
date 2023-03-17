package julotestcase.sanjaya.common.data.config

import julotestcase.sanjaya.common.data.DataGetter
import kotlinx.coroutines.flow.Flow

/**
 * An interface that defines a contract for classes that fetch and activate remote
 * config for an application.
 */
interface ConfigRepo : DataGetter {
    /**
     * Fetches the latest configuration data and emits a [Boolean] indicating whether the fetch was successful.
     *
     * @return A [Flow] that emits a [Boolean] indicating whether the fetch was successful.
     */
    fun fetch(): Flow<Boolean>
}
