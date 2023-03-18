package julotestcase.sanjaya.weather.location.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import julotestcase.sanjaya.common.utils.secondToLocalDateTime
import julotestcase.sanjaya.network.presentation.Refreshable
import julotestcase.sanjaya.ui.utils.initAnim
import julotestcase.sanjaya.ui.utils.repeatOnStarted
import julotestcase.sanjaya.weather.location.R
import julotestcase.sanjaya.weather.location.databinding.FragmentDashboardLocBinding
import julotestcase.sanjaya.weather.location.domain.pref.CurrentLocationUseCase
import julotestcase.sanjaya.weather.location.presentation.adapter.ForecastAdapter
import julotestcase.sanjaya.weather.location.presentation.router.LocationRouter
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class DashboardCurrentSetFragment : Fragment(R.layout.fragment_dashboard_loc), Refreshable {
    enum class State {
        NOT_FOUND, LOADING, SUCCESS, FAILED
    }

    private val binding by viewBinding(FragmentDashboardLocBinding::bind)

    @Inject
    lateinit var currentLocationUseCase: CurrentLocationUseCase

    @Inject
    lateinit var router: LocationRouter

    private val viewModel: ForecastViewModel by viewModels()
    private val todayAdapter by lazy { ForecastAdapter() }
    private val day1Adapter by lazy { ForecastAdapter() }
    private val day2Adapter by lazy { ForecastAdapter() }
    private val day3Adapter by lazy { ForecastAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flowCollectorSetup()
        binding.flipper.initAnim()
        binding.notFount.btnChoose.setOnClickListener {
            router.goToLocationSearch()
        }
        binding.error.btnRetry.setOnClickListener {
            refresh()
        }
        binding.success.btnChange.setOnClickListener {
            router.goToLocationSearch()
        }
        refresh()
    }

    private fun flowCollectorSetup() {
        binding.success.rvToday.adapter = todayAdapter
        binding.success.rvDay1.adapter = day1Adapter
        binding.success.rvDay2.adapter = day2Adapter
        binding.success.rvDay3.adapter = day3Adapter
        repeatOnStarted {
            viewModel.defaultLoading.collect {
                if (it) binding.flipper.displayedChild = State.LOADING.ordinal
            }
        }
        repeatOnStarted {
            viewModel.forecast.collect {
                binding.flipper.displayedChild = State.SUCCESS.ordinal
                todayAdapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                    }?.filterNotNull().orEmpty(),
                    binding.success.tvToday, true
                )
                day1Adapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                            .plusDays(1)
                    }?.filterNotNull().orEmpty(),
                    binding.success.tvDay1, false
                )
                day2Adapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                            .plusDays(2)
                    }?.filterNotNull().orEmpty(),
                    binding.success.tvDay2, false
                )
                day3Adapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                            .plusDays(3)
                    }?.filterNotNull().orEmpty(),
                    binding.success.tvDay3, false
                )
            }
        }
        repeatOnStarted {
            viewModel.defaultError.collect {
                if (!it.message.isNullOrBlank())
                    binding.flipper.displayedChild = State.FAILED.ordinal
                binding.error.tvErrorMsg.text = it.message
            }
        }
    }

    override fun refresh(onRefreshDone: () -> Unit) {
        lifecycleScope.launch {
            lifecycle.whenStateAtLeast(Lifecycle.State.STARTED) {
                val current = currentLocationUseCase.getCurrentLocation()
                if (current == null) binding.flipper.displayedChild = State.NOT_FOUND.ordinal
                else {
                    binding.success.tvName.text = current.name
                    binding.success.tvState.text = current.state
                    viewModel.getForecast(current.longitude ?: 0.0, current.latitude ?: 0.0)
                }
                super.refresh(onRefreshDone)
            }
        }
    }
}