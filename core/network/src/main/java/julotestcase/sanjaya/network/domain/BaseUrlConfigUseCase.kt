package julotestcase.sanjaya.network.domain

import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving the base URL configuration.
 */
interface BaseUrlConfigUseCase {
    /**
     * Returns a [Flow] that emits the current base URL.
     *
     * The base URL is used to construct network requests for accessing a remote API.
     * Clients of this use case can subscribe to the returned [Flow] to receive updates
     * whenever the base URL changes.
     *
     * @return A [Flow] of [String] values representing the base URL.
     */
    fun getBaseUrl(): Flow<String>
}
