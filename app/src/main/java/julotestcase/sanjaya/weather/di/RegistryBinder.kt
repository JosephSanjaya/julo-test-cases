package julotestcase.sanjaya.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.weather.dashboard.domain.content.DashboardContentGetter
import julotestcase.sanjaya.weather.registry.DashboardContentGetterImpl

/**
 * A Dagger module that binds implementations of Route to their respective Routers.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RegistryBinder {
    @Binds
    abstract fun bindDashboardGetter(
        impl: DashboardContentGetterImpl
    ): DashboardContentGetter
}
