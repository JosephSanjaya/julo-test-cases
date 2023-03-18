package julotestcase.sanjaya.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.weather.location.presentation.router.LocationRouter
import julotestcase.sanjaya.weather.router.location.LocationRouterImpl
import julotestcase.sanjaya.weather.router.splash.SplashExternalRouterImpl
import julotestcase.sanjaya.weather.splash.presentation.navigation.SplashExternalRoute

/**
 * A Dagger module that binds implementations of Route to their respective Routers.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RouterBinder {
    /**
     * Binds [SplashExternalRouterImpl] to [SplashExternalRoute].
     *
     * @param impl The implementation of [SplashExternalRoute] to bind.
     * @return The bound [SplashExternalRoute].
     */
    @Binds
    abstract fun bindSplashExternal(
        impl: SplashExternalRouterImpl
    ): SplashExternalRoute

    @Binds
    abstract fun bindLocRouter(
        impl: LocationRouterImpl
    ): LocationRouter
}
