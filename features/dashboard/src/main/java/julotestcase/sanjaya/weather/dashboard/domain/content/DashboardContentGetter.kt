package julotestcase.sanjaya.weather.dashboard.domain.content

import androidx.fragment.app.Fragment

interface DashboardContentGetter {
    fun getCurrentLocation(): Fragment
}