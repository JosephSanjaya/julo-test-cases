package julotestcase.sanjaya.weather.location.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.ToastUtils
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import julotestcase.sanjaya.ui.utils.initAnim
import julotestcase.sanjaya.ui.utils.repeatOnStarted
import julotestcase.sanjaya.weather.location.R
import julotestcase.sanjaya.weather.location.databinding.FragmentLocationSearchBinding
import julotestcase.sanjaya.weather.location.presentation.adapter.LocationSearchAdapter
import julotestcase.sanjaya.weather.location.presentation.router.LocationRouter
import javax.inject.Inject

@AndroidEntryPoint
class LocationSearchFragment : Fragment(R.layout.fragment_location_search),
    SwipeRefreshLayout.OnRefreshListener {

    enum class State {
        LOADING, SUCCESS, FAILURE
    }

    @Inject
    lateinit var router: LocationRouter
    private val binding by viewBinding(FragmentLocationSearchBinding::bind)
    private val adapter by lazy {
        LocationSearchAdapter({
            router.goToLocationForecast(it)
        }) { pos, data ->
            viewModel.setAsFavorite(pos, data)
        }
    }
    private val viewModel: LocationSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.flipper.initAnim()
        binding.swipeRefresh.setOnRefreshListener(this)
        binding.rvSearch.adapter = adapter
        setupFlowCollector()
        binding.failed.btnRetry.setOnClickListener {
            onRefresh()
        }
        binding.flipper.displayedChild = State.SUCCESS.ordinal
        binding.etSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH
                || i == EditorInfo.IME_ACTION_DONE
                || keyEvent.action == KeyEvent.ACTION_DOWN
                && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                viewModel.searchLocation(textView.text.toString())
                true
            } else false
        }
    }

    private fun setupFlowCollector() {
        repeatOnStarted {
            viewModel.defaultLoading.collect {
                if (it) binding.flipper.displayedChild = State.LOADING.ordinal
            }
        }
        repeatOnStarted {
            viewModel.searchState.collect {
                binding.flipper.displayedChild = State.SUCCESS.ordinal
                adapter.setNewInstance(it.toMutableList())
                binding.swipeRefresh.isRefreshing = false
            }
        }
        repeatOnStarted {
            viewModel.defaultError.collect {
                if (!it.message.isNullOrBlank())
                    binding.flipper.displayedChild = State.FAILURE.ordinal
                binding.failed.tvErrorMsg.text = it.message
            }
        }
        repeatOnStarted {
            viewModel.setAsFavoriteError.collect() {
                if (!it.message.isNullOrBlank()) ToastUtils.showLong(it.message)
            }
        }
        repeatOnStarted {
            viewModel.setAsFavoriteResult.collect() {
                adapter.setData(it.first, it.second)
            }
        }
    }

    override fun onRefresh() {
        viewModel.searchLocation(binding.etSearch.text?.toString().orEmpty())
    }
}