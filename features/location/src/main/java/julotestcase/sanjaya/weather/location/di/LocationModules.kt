package julotestcase.sanjaya.weather.location.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.weather.location.data.web.Get5DayForecastApi
import julotestcase.sanjaya.weather.location.data.web.SearchLocationApi
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocationModules {

    @Provides
    @Singleton
    fun provideSearchLocationApi(
        retrofit: Retrofit
    ): SearchLocationApi = retrofit.create(SearchLocationApi::class.java)


    @Provides
    @Singleton
    fun provide5DayForecastApi(
        retrofit: Retrofit
    ): Get5DayForecastApi= retrofit.create(Get5DayForecastApi::class.java)

}