package julotestcase.sanjaya.weather.location.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import julotestcase.sanjaya.network.presentation.BaseViewModel
import julotestcase.sanjaya.weather.location.data.models.LocationData
import julotestcase.sanjaya.weather.location.domain.location.LocationUseCase
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase
) : BaseViewModel() {
    val searchState by state("search", listOf<LocationData>())

    val setAsFavoriteResult by state("set-as-favorite", 0 to LocationData())
    val setAsFavoriteError by state("set-as-favorite", Throwable())

    fun searchLocation(cityName: String) = executeRoutine("search") {
        locationUseCase.searchLocationAsync(cityName).await()
    }

    @Suppress("DeferredResultUnused")
    fun setAsFavorite(position: Int, location: LocationData) =
        executeRoutine("set-as-favorite", useDefaultLoading = false, useDefaultError = false) {
            locationUseCase.setAsFavoriteAsync(location)
            position to location
        }
}