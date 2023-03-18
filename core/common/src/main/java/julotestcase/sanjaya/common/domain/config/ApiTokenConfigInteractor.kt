package julotestcase.sanjaya.common.domain.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

/**
 * Interactor class that implements the ApiTokenConfigUseCase interface and retrieves the API token from the repository.
 *
 * @param repo The repository instance used to retrieve the API token value.
 */
class ApiTokenConfigInteractor @Inject constructor(
    private val config: FirebaseRemoteConfig
) : ApiTokenConfigUseCase {
    /**
     * Retrieves the API token from the repository and returns it as a flow.
     *
     * @return A flow that emits the API token value when it is available.
     */
    override fun getOpenWeatherApiKey(): String = config.getString(OPEN_WEATHER_API_TOKEN_KEY)
    override fun getApiNinjaApiKey(): String = config.getString(API_NINJA_API_TOKEN_KEY)

    companion object {
        const val OPEN_WEATHER_API_TOKEN_KEY =
            "open_weather_api_token"  // The key used to retrieve the API token value from the repository.
        const val API_NINJA_API_TOKEN_KEY =
            "api_ninja_api_token"  // The key used to retrieve the API token value from the repository.
    }
}
