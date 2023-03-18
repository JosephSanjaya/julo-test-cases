package julotestcase.sanjaya.weather.location.presentation.router

import android.os.Bundle
import julotestcase.sanjaya.weather.location.data.models.LocationData

interface LocationRouter {
    fun goToLocationSearch()
    fun goToLocationForecast(location: LocationData)
    fun getLocationDataFromArgs(arguments: Bundle?): LocationData
    fun selectMainLocation()
}