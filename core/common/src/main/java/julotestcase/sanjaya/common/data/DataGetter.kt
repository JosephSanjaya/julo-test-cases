package julotestcase.sanjaya.common.data

import kotlinx.coroutines.flow.Flow

/**
 * An interface for retrieving data of different types as [Flow]s.
 */
interface DataGetter {
    /**
     * Retrieves a [Flow] of [String] associated with the given [key].
     *
     * @param key The key associated with the [String] to retrieve.
     * @return A [Flow] of [String] associated with the given [key].
     */
    fun getString(key: String): Flow<String>

    /**
     * Retrieves a [Flow] of [Long] associated with the given [key].
     *
     * @param key The key associated with the [Long] to retrieve.
     * @return A [Flow] of [Long] associated with the given [key].
     */
    fun getLong(key: String): Flow<Long>

    /**
     * Retrieves a [Flow] of [Int] associated with the given [key].
     *
     * @param key The key associated with the [Int] to retrieve.
     * @return A [Flow] of [Int] associated with the given [key].
     */
    fun getInt(key: String): Flow<Int>

    /**
     * Retrieves a [Flow] of [Double] associated with the given [key].
     *
     * @param key The key associated with the [Double] to retrieve.
     * @return A [Flow] of [Double] associated with the given [key].
     */
    fun getDouble(key: String): Flow<Double>
}
