package julotestcase.sanjaya.weather.location.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import julotestcase.sanjaya.network.presentation.BaseViewModel
import julotestcase.sanjaya.weather.location.data.SearchLocationRepo
import julotestcase.sanjaya.weather.location.data.models.LocationData
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val searchLocationRepo: SearchLocationRepo
): BaseViewModel() {
    val searchState by state("search", listOf<LocationData>())

    fun searchLocation(cityName: String) = executeRoutine("search") {
        searchLocationRepo.searchLocationAsync(cityName).await()
    }
}