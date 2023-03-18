package julotestcase.sanjaya.weather.location.presentation.adapter

import androidx.core.view.isGone
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.material.textview.MaterialTextView
import com.pluto.utilities.extensions.capitalizeText
import julotestcase.sanjaya.common.utils.secondToLocalDateTime
import julotestcase.sanjaya.weather.location.R
import julotestcase.sanjaya.weather.location.data.models.ForecastData
import julotestcase.sanjaya.weather.location.databinding.LayoutRowDashboardLocBinding
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ForecastAdapter :
    BaseQuickAdapter<ForecastData.ListItem, BaseDataBindingHolder<LayoutRowDashboardLocBinding>>(
        R.layout.layout_row_dashboard_loc,
        mutableListOf()
    ) {

    init {
        animationEnable = true
        setAnimationWithDefault(AnimationType.SlideInBottom)
    }

    override fun convert(
        holder: BaseDataBindingHolder<LayoutRowDashboardLocBinding>,
        item: ForecastData.ListItem
    ) {
        holder.dataBinding?.apply {
            val temp = "${item.main?.temp ?: 0.0} F"
            val date = item.dt?.secondToLocalDateTime()
            tvTime.text = date?.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
            tvTemp.text = temp
            tvHumid.text = (item.main?.humidity ?: 0.0).toString()
            tvWind.text = (item.wind?.speed ?: 0.0).toString()
        }
    }

    fun setupForecastData(
        filteredData: List<ForecastData.ListItem>,
        tv: MaterialTextView,
        isSetAsToday: Boolean = false
    ) {
        if (!isSetAsToday) {
            tv.text = filteredData.firstOrNull()?.dt?.secondToLocalDateTime()
                ?.dayOfWeek?.name?.lowercase()
                ?.capitalizeText()
        }
        if (!isSetAsToday) {
            recyclerView.isGone = filteredData.isEmpty()
            tv.isGone = filteredData.isEmpty()
        }
        setNewInstance(filteredData.toMutableList())
    }

}