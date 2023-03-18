package julotestcase.sanjaya.weather.location.domain.pref

import android.content.SharedPreferences
import julotestcase.sanjaya.common.utils.toJson
import julotestcase.sanjaya.common.utils.toObject
import julotestcase.sanjaya.weather.location.data.models.LocationData
import javax.inject.Inject

class CurrentLocationInteractor @Inject constructor(
    private val prefRepo: SharedPreferences
) : CurrentLocationUseCase {
    override fun getCurrentLocation(): LocationData? {
        return prefRepo.getString(CurrentLocationUseCase.CURRENT_LOCATION_KEY, "")?.toObject()
    }

    override suspend fun setCurrentLocation(locationData: LocationData) {
        prefRepo.edit()
            .putString(CurrentLocationUseCase.CURRENT_LOCATION_KEY, locationData.toJson())
            .apply()
    }
}