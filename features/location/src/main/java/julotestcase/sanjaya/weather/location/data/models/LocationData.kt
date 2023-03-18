package julotestcase.sanjaya.weather.location.data.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(

    @field:SerializedName("country")
    @Expose
    val country: String? = null,

    @field:SerializedName("latitude")
    @Expose
    val latitude: Double? = null,

    @field:SerializedName("name")
    @Expose
    val name: String? = null,

    @field:SerializedName("state")
    @Expose
    val state: String? = null,

    @field:SerializedName("longitude")
    @Expose
    val longitude: Double? = null
): Parcelable
