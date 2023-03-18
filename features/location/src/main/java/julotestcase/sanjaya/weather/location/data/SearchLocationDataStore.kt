package julotestcase.sanjaya.weather.location.data

import julotestcase.sanjaya.common.domain.config.ApiTokenConfigUseCase
import julotestcase.sanjaya.weather.location.data.models.LocationData
import julotestcase.sanjaya.weather.location.data.web.SearchLocationApi
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class SearchLocationDataStore @Inject constructor(
    private val api: SearchLocationApi,
    private val tokenConfigUseCase: ApiTokenConfigUseCase
) : SearchLocationRepo {
    override fun searchLocationAsync(city: String): Deferred<List<LocationData>> {
        return api.searchLocationAsync(tokenConfigUseCase.getApiNinjaApiKey(), city)
    }
}