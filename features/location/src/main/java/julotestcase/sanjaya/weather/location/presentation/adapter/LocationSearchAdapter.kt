package julotestcase.sanjaya.weather.location.presentation.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import julotestcase.sanjaya.weather.location.R
import julotestcase.sanjaya.weather.location.data.models.LocationData
import julotestcase.sanjaya.weather.location.databinding.LayoutRowCityBinding

class LocationSearchAdapter(
    private val onClick: (LocationData) -> Unit = {},
    private val onFavoriteClick: (LocationData) -> Unit = {}
) :
    BaseQuickAdapter<LocationData, BaseDataBindingHolder<LayoutRowCityBinding>>(
        R.layout.layout_row_city,
        mutableListOf()
    ), OnItemChildClickListener, OnItemClickListener {

    init {
        animationEnable = true
        setAnimationWithDefault(AnimationType.SlideInBottom)
        setOnItemClickListener(this)
        setOnItemChildClickListener(this)
    }

    override fun convert(
        holder: BaseDataBindingHolder<LayoutRowCityBinding>,
        item: LocationData
    ) {
        holder.dataBinding?.apply {
            tvCityName.text = item.name
            tvState.text = item.state
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when (view.id) {
            R.id.cvRoot -> onClick(getItem(position))
            R.id.btnFavorite -> onFavoriteClick(getItem(position))
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        onClick(getItem(position))
    }
}