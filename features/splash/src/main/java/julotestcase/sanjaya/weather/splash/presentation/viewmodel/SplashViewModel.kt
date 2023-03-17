package julotestcase.sanjaya.weather.splash.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import julotestcase.sanjaya.common.data.config.ConfigRepo
import julotestcase.sanjaya.network.presentation.BaseViewModel
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

/**
 * ViewModel for the splash screen. Fetches configuration data from the server.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val configRepo: ConfigRepo
) : BaseViewModel() {
    /**
     * A boolean state variable that indicates whether data is currently being fetched.
     */
    val fetchState by state("fetch", false)

    /**
     * Launches a coroutine to fetch configuration data from the server using [ConfigRepo].
     */
    fun fetch() = executeRoutine("fetch") {
        configRepo.fetch().firstOrNull() ?: false
    }
}
