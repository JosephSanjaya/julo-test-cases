package julotestcase.sanjaya.weather.location.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import julotestcase.sanjaya.common.utils.secondToLocalDateTime
import julotestcase.sanjaya.ui.utils.initAnim
import julotestcase.sanjaya.ui.utils.repeatOnStarted
import julotestcase.sanjaya.weather.location.R
import julotestcase.sanjaya.weather.location.databinding.FragmentLocationForecastBinding
import julotestcase.sanjaya.weather.location.domain.pref.CurrentLocationUseCase
import julotestcase.sanjaya.weather.location.presentation.adapter.ForecastAdapter
import julotestcase.sanjaya.weather.location.presentation.router.LocationRouter
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class LocationForecastFragment : Fragment(R.layout.fragment_location_forecast),
    SwipeRefreshLayout.OnRefreshListener {
    enum class State {
        LOADING, SUCCESS, FAILED
    }

    private val binding by viewBinding(FragmentLocationForecastBinding::bind)

    private val data by lazy {
        router.getLocationDataFromArgs(arguments)
    }

    @Inject
    lateinit var router: LocationRouter

    @Inject
    lateinit var currentLocationUseCase: CurrentLocationUseCase
    private val viewModel: ForecastViewModel by viewModels()
    private val todayAdapter by lazy { ForecastAdapter() }
    private val day1Adapter by lazy { ForecastAdapter() }
    private val day2Adapter by lazy { ForecastAdapter() }
    private val day3Adapter by lazy { ForecastAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = data.name
        binding.tvState.text = data.state
        binding.flipper.initAnim()
        flowCollectorSetup()
        binding.error.btnRetry.setOnClickListener {
            onRefresh()
        }
        binding.btnSetAsMain.setOnClickListener {
            lifecycleScope.launch {
                currentLocationUseCase.setCurrentLocation(data)
            }
            router.selectMainLocation()
        }
        binding.swipeRefresh.setOnRefreshListener(this)
        onRefresh()
    }

    private fun flowCollectorSetup() {
        binding.rvToday.adapter = todayAdapter
        binding.rvDay1.adapter = day1Adapter
        binding.rvDay2.adapter = day2Adapter
        binding.rvDay3.adapter = day3Adapter
        repeatOnStarted {
            viewModel.defaultLoading.collect {
                if (it) binding.flipper.displayedChild =
                    State.LOADING.ordinal
                binding.swipeRefresh.isRefreshing = false
            }
        }
        repeatOnStarted {
            viewModel.forecast.collect {
                binding.flipper.displayedChild = State.SUCCESS.ordinal
                todayAdapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                    }?.filterNotNull().orEmpty(),
                    binding.tvToday, true
                )
                day1Adapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                            .plusDays(1)
                    }?.filterNotNull().orEmpty(),
                    binding.tvDay1, false
                )
                day2Adapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                            .plusDays(2)
                    }?.filterNotNull().orEmpty(),
                    binding.tvDay2, false
                )
                day3Adapter.setupForecastData(
                    it.list?.filter { list ->
                        list?.dt?.secondToLocalDateTime()?.toLocalDate() == LocalDate.now()
                            .plusDays(3)
                    }?.filterNotNull().orEmpty(),
                    binding.tvDay3, false
                )
            }
        }
        repeatOnStarted {
            viewModel.defaultError.collect {
                if (!it.message.isNullOrBlank())
                    binding.flipper.displayedChild =
                        State.FAILED.ordinal
                binding.error.tvErrorMsg.text = it.message
            }
        }
    }

    override fun onRefresh() {
        viewModel.getForecast(data.longitude ?: 0.0, data.latitude ?: 0.0)
    }

}