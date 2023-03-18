package julotestcase.sanjaya.weather.registry

import androidx.fragment.app.Fragment
import julotestcase.sanjaya.weather.dashboard.domain.content.DashboardContentGetter
import julotestcase.sanjaya.weather.location.presentation.DashboardCurrentSetFragment
import javax.inject.Inject

class DashboardContentGetterImpl @Inject constructor() : DashboardContentGetter {
    override fun getCurrentLocation(): Fragment {
        return DashboardCurrentSetFragment()
    }
}