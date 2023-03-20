package julotestcase.sanjaya.weather.location.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import julotestcase.sanjaya.weather.location.data.models.LocationData.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class LocationData(

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @field:SerializedName("country")
    @ColumnInfo("country")
    @Expose
    var country: String? = null,

    @field:SerializedName("latitude")
    @ColumnInfo("latitude")
    @Expose
    var latitude: Double? = null,

    @field:SerializedName("name")
    @ColumnInfo("name")
    @Expose
    var name: String? = null,

    @field:SerializedName("state")
    @ColumnInfo("state")
    @Expose
    var state: String? = null,

    @field:SerializedName("longitude")
    @ColumnInfo("longitude")
    @Expose
    var longitude: Double? = null,

    @Ignore
    var isFavorite: Boolean = false
) : Parcelable {

    constructor() : this(0, null, null, null, null, null, false)

    companion object {
        const val TABLE_NAME = "favorite_table"
    }
}
