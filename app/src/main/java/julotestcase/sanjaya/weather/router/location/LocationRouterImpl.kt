package julotestcase.sanjaya.weather.router.location

import android.os.Bundle
import androidx.navigation.findNavController
import com.blankj.utilcode.util.ActivityUtils
import julotestcase.sanjaya.weather.R
import julotestcase.sanjaya.weather.location.data.models.LocationData
import julotestcase.sanjaya.weather.location.presentation.LocationForecastFragmentArgs
import julotestcase.sanjaya.weather.location.presentation.router.LocationRouter
import javax.inject.Inject

class LocationRouterImpl @Inject constructor() : LocationRouter {
    override fun goToLocationSearch() {
        ActivityUtils.getTopActivity().findNavController(R.id.navHost)
            .navigate(R.id.action_dashboardFragment_to_locationSearchFragment)
    }

    override fun goToLocationForecast(location: LocationData) {
        ActivityUtils.getTopActivity().findNavController(R.id.navHost)
            .navigate(
                R.id.action_locationSearchFragment_to_locationForecastFragment,
                LocationForecastFragmentArgs(location).toBundle()
            )
    }

    override fun getLocationDataFromArgs(arguments: Bundle?): LocationData {
        if (arguments == null) return LocationData()
        return LocationForecastFragmentArgs.fromBundle(arguments).location
    }

    override fun selectMainLocation() {
        ActivityUtils.getTopActivity().findNavController(R.id.navHost)
            .navigate(R.id.action_locationForecastFragment_to_dashboardFragment)
    }
}