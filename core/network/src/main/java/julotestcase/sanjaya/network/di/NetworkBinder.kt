package julotestcase.sanjaya.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import julotestcase.sanjaya.network.domain.BaseUrlConfigUseCase
import julotestcase.sanjaya.network.domain.BaseUrlInteractor

/**
 * Dagger module for binding network dependencies at the application level.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBinder {
    /**
     * Binds an [BaseUrlConfigUseCase] implementation to the [BaseUrlInteractor] dependency.
     *
     * @param interactor The interactor implementation to be used by the use case.
     * @return An instance of [BaseUrlConfigUseCase].
     */
    @Binds
    abstract fun bindBaseUrl(
        interactor: BaseUrlInteractor
    ): BaseUrlConfigUseCase
}
