package julotestcase.sanjaya.weather.location.data

import julotestcase.sanjaya.weather.location.data.models.LocationData
import kotlinx.coroutines.Deferred

interface SearchLocationRepo {
    fun searchLocationAsync(city: String): Deferred<List<LocationData>>
}