package julotestcase.sanjaya.weather.splash.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.AndroidEntryPoint
import julotestcase.sanjaya.ui.utils.repeatOnStarted
import julotestcase.sanjaya.weather.splash.R
import julotestcase.sanjaya.weather.splash.presentation.navigation.SplashExternalRoute
import javax.inject.Inject

/**
 * A fragment that displays a splash screen while loading data.
 */
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    /**
     * External route used to navigate to the main dashboard screen.
     */
    @Inject
    lateinit var router: SplashExternalRoute
    @Inject
    lateinit var config: FirebaseRemoteConfig

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatOnStarted {
            config.fetchAndActivate().addOnCompleteListener {
                router.goToDashboard(requireContext())
            }
        }
    }
}
