package julotestcase.sanjaya.weather.location.domain.location

import julotestcase.sanjaya.weather.location.data.models.LocationData
import kotlinx.coroutines.Deferred

interface LocationUseCase {
    suspend fun searchLocationAsync(cityName: String): Deferred<List<LocationData>>
    suspend fun setAsFavoriteAsync(locationData: LocationData)
}