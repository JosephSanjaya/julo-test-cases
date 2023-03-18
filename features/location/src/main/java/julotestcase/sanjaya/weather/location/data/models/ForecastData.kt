package julotestcase.sanjaya.weather.location.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastData(

    @Expose
    @field:SerializedName("city")
    val city: City? = null,

    @Expose
    @field:SerializedName("cnt")
    val cnt: Int? = null,

    @Expose
    @field:SerializedName("cod")
    val cod: String? = null,

    @Expose
    @field:SerializedName("message")
    val message: Int? = null,

    @Expose
    @field:SerializedName("list")
    val list: List<ListItem?>? = null
) : Parcelable {

    @Parcelize
    data class WeatherItem(

        @Expose
        @field:SerializedName("icon")
        val icon: String? = null,

        @Expose
        @field:SerializedName("description")
        val description: String? = null,

        @Expose
        @field:SerializedName("main")
        val main: String? = null,

        @Expose
        @field:SerializedName("id")
        val id: Int? = null
    ) : Parcelable

    @Parcelize
    data class ListItem(

        @Expose
        @field:SerializedName("dt")
        val dt: Long? = null,

        @Expose
        @field:SerializedName("pop")
        val pop: Double? = null,

        @Expose
        @field:SerializedName("visibility")
        val visibility: Int? = null,

        @Expose
        @field:SerializedName("dt_txt")
        val dtTxt: String? = null,

        @Expose
        @field:SerializedName("weather")
        val weather: List<WeatherItem?>? = null,

        @Expose
        @field:SerializedName("main")
        val main: Main? = null,

        @Expose
        @field:SerializedName("clouds")
        val clouds: Clouds? = null,

        @Expose
        @field:SerializedName("sys")
        val sys: Sys? = null,

        @Expose
        @field:SerializedName("wind")
        val wind: Wind? = null,

        @Expose
        @field:SerializedName("rain")
        val rain: Rain? = null
    ) : Parcelable

    @Parcelize
    data class Coord(

        @Expose
        @field:SerializedName("lon")
        val lon: Double? = null,

        @Expose
        @field:SerializedName("lat")
        val lat: Double? = null
    ) : Parcelable

    @Parcelize
    data class City(

        @Expose
        @field:SerializedName("country")
        val country: String? = null,

        @Expose
        @field:SerializedName("coord")
        val coord: Coord? = null,

        @Expose
        @field:SerializedName("sunrise")
        val sunrise: Int? = null,

        @Expose
        @field:SerializedName("timezone")
        val timezone: Int? = null,

        @Expose
        @field:SerializedName("sunset")
        val sunset: Int? = null,

        @Expose
        @field:SerializedName("name")
        val name: String? = null,

        @Expose
        @field:SerializedName("id")
        val id: Int? = null,

        @Expose
        @field:SerializedName("population")
        val population: Int? = null
    ) : Parcelable

    @Parcelize
    data class Clouds(

        @Expose
        @field:SerializedName("all")
        val all: Int? = null
    ) : Parcelable

    @Parcelize
    data class Wind(

        @Expose
        @field:SerializedName("deg")
        val deg: Int? = null,

        @Expose
        @field:SerializedName("speed")
        val speed: Double? = null,

        @Expose
        @field:SerializedName("gust")
        val gust: Double? = null
    ) : Parcelable

    @Parcelize
    data class Main(

        @Expose
        @field:SerializedName("temp")
        val temp: Double? = null,

        @Expose
        @field:SerializedName("temp_min")
        val tempMin: Double? = null,

        @Expose
        @field:SerializedName("grnd_level")
        val grndLevel: Int? = null,

        @Expose
        @field:SerializedName("temp_kf")
        val tempKf: Double? = null,

        @Expose
        @field:SerializedName("humidity")
        val humidity: Int? = null,

        @Expose
        @field:SerializedName("pressure")
        val pressure: Int? = null,

        @Expose
        @field:SerializedName("sea_level")
        val seaLevel: Int? = null,

        @Expose
        @field:SerializedName("feels_like")
        val feelsLike: Double? = null,

        @Expose
        @field:SerializedName("temp_max")
        val tempMax: Double? = null
    ) : Parcelable

    @Parcelize
    data class Rain(

        @Expose
        @field:SerializedName("3h")
        val jsonMember3h: Double? = null
    ) : Parcelable

    @Parcelize
    data class Sys(

        @Expose
        @field:SerializedName("pod")
        val pod: String? = null
    ) : Parcelable

}
