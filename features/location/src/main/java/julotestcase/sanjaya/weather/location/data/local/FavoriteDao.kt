package julotestcase.sanjaya.weather.location.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import julotestcase.sanjaya.weather.location.data.models.LocationData

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${LocationData.TABLE_NAME}")
    suspend fun getAll(): List<LocationData>

    @Insert
    suspend fun insertAll(vararg location: LocationData)

    @Delete
    suspend fun delete(location: LocationData)
}