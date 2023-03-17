package julotestcase.sanjaya.weather.splash.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import julotestcase.sanjaya.weather.splash.R
import julotestcase.sanjaya.weather.splash.presentation.navigation.SplashExternalRoute
import julotestcase.sanjaya.weather.splash.presentation.viewmodel.SplashViewModel

import dagger.hilt.android.AndroidEntryPoint
import julotestcase.sanjaya.ui.utils.repeatOnStarted

import javax.inject.Inject

/**
 * A fragment that displays a splash screen while loading data.
 */
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    /**
     * View model used to load data and update the UI.
     */
    private val viewModel: SplashViewModel by viewModels()

    /**
     * External route used to navigate to the main dashboard screen.
     */
    @Inject
    lateinit var router: SplashExternalRoute

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatOnStarted {
            viewModel.fetchState.collect {
                router.goToDashboard(requireContext())
            }
        }
        viewModel.fetch()
    }
}
