package julotestcase.sanjaya.weather.location.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.weather.location.data.Get5DayForecastDataStore
import julotestcase.sanjaya.weather.location.data.Get5DayForecastRepo
import julotestcase.sanjaya.weather.location.data.SearchLocationDataStore
import julotestcase.sanjaya.weather.location.data.SearchLocationRepo
import julotestcase.sanjaya.weather.location.domain.location.LocationInteractor
import julotestcase.sanjaya.weather.location.domain.location.LocationUseCase
import julotestcase.sanjaya.weather.location.domain.pref.CurrentLocationInteractor
import julotestcase.sanjaya.weather.location.domain.pref.CurrentLocationUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationBinder {
    @Binds
    abstract fun bindSearchLocationDataSource(
        dataStore: SearchLocationDataStore
    ): SearchLocationRepo

    @Binds
    abstract fun bindLocationInteractor(
        interactor: LocationInteractor
    ): LocationUseCase

    @Binds
    abstract fun bindCurrentLocation(
        interactor: CurrentLocationInteractor
    ): CurrentLocationUseCase

    @Binds
    abstract fun bind5DayForecastRepo(
        dataStore: Get5DayForecastDataStore
    ): Get5DayForecastRepo
}