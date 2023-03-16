package julotestcase.sanjaya.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.common.data.config.ConfigDataStore
import julotestcase.sanjaya.common.data.config.ConfigRepo
import julotestcase.sanjaya.common.data.pref.PrefDataStore
import julotestcase.sanjaya.common.data.pref.PrefRepo
import julotestcase.sanjaya.common.domain.config.ApiTokenConfigInteractor
import julotestcase.sanjaya.common.domain.config.ApiTokenConfigUseCase

/**
 * Dagger module for binding common dependencies at the application level.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CommonBinder {
    /**
     * Binds a [ConfigRepo] implementation to the [ConfigDataStore] dependency.
     *
     * @param dataStore The data store implementation to be used by the repository.
     * @return An instance of [ConfigRepo].
     */
    @Binds
    abstract fun bindConfig(
        dataStore: ConfigDataStore
    ): ConfigRepo

    /**
     * Binds an [ApiTokenConfigUseCase] implementation to the [ApiTokenConfigInteractor] dependency.
     *
     * @param interactor The interactor implementation to be used by the use case.
     * @return An instance of [ApiTokenConfigUseCase].
     */
    @Binds
    abstract fun bindApiTokenUseCase(
        interactor: ApiTokenConfigInteractor
    ): ApiTokenConfigUseCase

    /**
     * Binds a [PrefDataStore] instance to a [PrefRepo] interface.
     *
     * @param dataStore The [PrefDataStore] instance to bind.
     * @return A [PrefRepo] implementation that uses the provided [PrefDataStore].
     */
    @Binds
    abstract fun bindPref(
        dataStore: PrefDataStore
    ): PrefRepo
}
