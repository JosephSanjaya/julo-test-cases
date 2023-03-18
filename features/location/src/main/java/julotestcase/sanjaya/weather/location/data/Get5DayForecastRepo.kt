package julotestcase.sanjaya.weather.location.data

import julotestcase.sanjaya.weather.location.data.models.ForecastData
import kotlinx.coroutines.Deferred

interface Get5DayForecastRepo {
    fun getForecastAsync(lon: Double, lat: Double): Deferred<ForecastData>
}