package julotestcase.sanjaya.network.domain

import julotestcase.sanjaya.common.data.config.ConfigRepo

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow

/**
 * Interactor implementation of [BaseUrlConfigUseCase].
 *
 * This implementation retrieves the base URL configuration from the provided [ConfigRepo]
 * and exposes it as a [Flow] of [String] values.
 *
 * @param repo The [ConfigRepo] implementation to use for retrieving the base URL configuration.
 */
class BaseUrlInteractor @Inject constructor(
    private val repo: ConfigRepo
) : BaseUrlConfigUseCase {
    override fun getBaseUrl(): Flow<String> = repo.getString(BASE_URL)

    companion object {
        /**
         * The key used to retrieve the base URL configuration from the [ConfigRepo].
         */
        const val BASE_URL = "base-url"
    }
}
