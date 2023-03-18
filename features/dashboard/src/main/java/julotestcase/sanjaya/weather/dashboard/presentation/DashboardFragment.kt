package julotestcase.sanjaya.weather.dashboard.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import julotestcase.sanjaya.network.presentation.Refreshable
import julotestcase.sanjaya.weather.dashboard.R
import julotestcase.sanjaya.weather.dashboard.databinding.FragmentDashboardBinding
import julotestcase.sanjaya.weather.dashboard.domain.content.DashboardContentGetter
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard),
    SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val CURRENT_LOC = "currentLoc"
    }

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    @Inject
    lateinit var contentGetter: DashboardContentGetter
    private val fragmentRegistry = hashMapOf<String, Fragment>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener(this)
        addOrRefreshCurrentLoc()
    }

    private fun addOrRefreshCurrentLoc() {
        if (fragmentRegistry.containsKey(CURRENT_LOC)) {
            runCatching {
                fragmentRegistry[CURRENT_LOC] as Refreshable
            }.getOrNull()?.refresh()
        } else {
            fragmentRegistry[CURRENT_LOC] = contentGetter.getCurrentLocation()
            fragmentRegistry[CURRENT_LOC]?.let {
                childFragmentManager.beginTransaction()
                    .replace(binding.currentLoc.id, it)
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun onRefresh() {
        fragmentRegistry.forEach { (_, fragment) ->
            if (fragment is Refreshable) fragment.refresh {
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}