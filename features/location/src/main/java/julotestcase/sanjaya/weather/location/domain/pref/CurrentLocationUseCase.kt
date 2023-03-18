package julotestcase.sanjaya.weather.location.domain.pref

import julotestcase.sanjaya.weather.location.data.models.LocationData

interface CurrentLocationUseCase {

    fun getCurrentLocation(): LocationData?
    suspend fun setCurrentLocation(locationData: LocationData)

    companion object {
        const val CURRENT_LOCATION_KEY = "current-location"
    }
}