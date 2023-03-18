package julotestcase.sanjaya.network.domain

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import julotestcase.sanjaya.network.domain.BaseUrlConfigUseCase.Companion.API_NINJA_BASE_URL
import julotestcase.sanjaya.network.domain.BaseUrlConfigUseCase.Companion.OPEN_WEATHER_BASE_URL
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Interactor implementation of [BaseUrlConfigUseCase].
 *
 * This implementation retrieves the base URL configuration from the provided [FirebaseRemoteConfig]
 * and exposes it as a [Flow] of [String] values.
 *
 * @param repo The [FirebaseRemoteConfig] implementation to use for retrieving the base URL configuration.
 */
class BaseUrlInteractor @Inject constructor(
    private val config: FirebaseRemoteConfig
) : BaseUrlConfigUseCase {
    override fun getOpenWeatherBaseUrl(): String = config.getString(OPEN_WEATHER_BASE_URL)
    override fun getApiNinjaBaseUrl(): String = config.getString(API_NINJA_BASE_URL)

}
