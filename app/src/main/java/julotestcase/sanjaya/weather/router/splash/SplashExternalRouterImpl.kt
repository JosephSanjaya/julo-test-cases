package julotestcase.sanjaya.weather.router.splash

import android.content.Context
import androidx.navigation.findNavController
import com.blankj.utilcode.util.ActivityUtils
import julotestcase.sanjaya.weather.R
import julotestcase.sanjaya.weather.splash.presentation.navigation.SplashExternalRoute
import javax.inject.Inject

/**
 * An implementation of [SplashExternalRoute] that navigates to the dashboard screen.
 *
 * This implementation uses the [Context] to start the activity that displays the dashboard.
 */
class SplashExternalRouterImpl @Inject constructor() : SplashExternalRoute {
    /**
     * Navigates to the dashboard screen.
     *
     * This implementation starts the activity that displays the dashboard screen.
     *
     * @param context The [Context] used to start the dashboard activity.
     */
    override fun goToDashboard(context: Context) {
        ActivityUtils.getTopActivity().findNavController(R.id.navHost)
            .navigate(R.id.action_splashFragment_to_dashboardFragment)
    }
}
