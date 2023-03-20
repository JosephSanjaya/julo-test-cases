package julotestcase.sanjaya.weather.location.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import julotestcase.sanjaya.weather.location.data.models.LocationData

@Database(entities = [LocationData::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DATABASE_NAME = "location_db"
    }
}