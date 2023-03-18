package julotestcase.sanjaya.weather.location.data.web

import julotestcase.sanjaya.network.domain.BaseUrlConfigUseCase
import julotestcase.sanjaya.weather.location.data.models.LocationData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchLocationApi {

    @Headers("Domain-Name: ${BaseUrlConfigUseCase.API_NINJA_BASE_URL}")
    @GET("geocoding")
    fun searchLocationAsync(
        @Header("X-Api-Key") apiKey: String,
        @Query("city") city: String
    ): Deferred<List<LocationData>>
}