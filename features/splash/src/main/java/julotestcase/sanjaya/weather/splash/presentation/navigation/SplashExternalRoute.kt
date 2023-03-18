package julotestcase.sanjaya.weather.splash.presentation.navigation

import android.content.Context

/**
 * Defines a contract for navigating to the dashboard screen from an external source.
 */
interface SplashExternalRoute {
    /**
     * Navigates to the dashboard screen using the provided context.
     *
     * @param context the context used to start the dashboard activity
     */
    fun goToDashboard(context: Context)
}
