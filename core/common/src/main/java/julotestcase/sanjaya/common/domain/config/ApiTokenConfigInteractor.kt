package julotestcase.sanjaya.common.domain.config

import julotestcase.sanjaya.common.data.config.ConfigRepo

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow

/**
 * Interactor class that implements the ApiTokenConfigUseCase interface and retrieves the API token from the repository.
 *
 * @param repo The repository instance used to retrieve the API token value.
 */
class ApiTokenConfigInteractor @Inject constructor(
    private val repo: ConfigRepo
) : ApiTokenConfigUseCase {
    /**
     * Retrieves the API token from the repository and returns it as a flow.
     *
     * @return A flow that emits the API token value when it is available.
     */
    override fun getApiToken(): Flow<String> = repo.getString(API_TOKEN_KEY)

    companion object {
        const val API_TOKEN_KEY =
            "api_token"  // The key used to retrieve the API token value from the repository.
    }
}
