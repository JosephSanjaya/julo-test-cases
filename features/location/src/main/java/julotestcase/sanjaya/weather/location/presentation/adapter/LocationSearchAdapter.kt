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
    private val onFavoriteClick: (Int, LocationData) -> Unit = { _, _ -> }
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
        addChildClickViewIds(R.id.cvRoot, R.id.btnFavorite)
    }

    override fun convert(
        holder: BaseDataBindingHolder<LayoutRowCityBinding>,
        item: LocationData
    ) {
        holder.dataBinding?.apply {
            tvCityName.text = item.name
            tvState.text = item.state
            btnFavorite.setImageResource(
                if (item.isFavorite)
                    julotestcase.sanjaya.ui.R.drawable.baseline_favorite_24 else
                    julotestcase.sanjaya.ui.R.drawable.baseline_favorite_border_24
            )
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when (view.id) {
            R.id.cvRoot -> onClick(getItem(position))
            R.id.btnFavorite -> onFavoriteClick(position, getItem(position))
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        onClick(getItem(position))
    }
}