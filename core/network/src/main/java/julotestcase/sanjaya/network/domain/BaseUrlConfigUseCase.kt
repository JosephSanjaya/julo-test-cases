package julotestcase.sanjaya.network.domain

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
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
    fun getOpenWeatherBaseUrl(): String
    fun getApiNinjaBaseUrl(): String

    companion object {
        /**
         * The key used to retrieve the base URL configuration from the [FirebaseRemoteConfig].
         */
        const val OPEN_WEATHER_BASE_URL = "open_weather_base_url"
        const val API_NINJA_BASE_URL = "api_ninja_base_url"
    }
}
