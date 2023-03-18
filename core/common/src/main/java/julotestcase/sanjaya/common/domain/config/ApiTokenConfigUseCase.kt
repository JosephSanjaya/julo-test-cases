package julotestcase.sanjaya.common.domain.config

import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving an API token string.
 */
interface ApiTokenConfigUseCase {
    /**
     * Returns a [Flow] that emits the current API token string.
     * The [Flow] will emit a new value whenever the API token is updated.
     *
     * @return A [Flow] that emits the current API token string.
     */
    fun getOpenWeatherApiKey(): String
    fun getApiNinjaApiKey(): String
}
