package com.allen.weather.ui.pages.dailyweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.weather.R
import com.allen.weather.extension.weatherTimeToDailyFormat
import com.allen.weather.ui.common.recyclerview.BaseViewBinder
import com.allen.weather.ui.common.recyclerview.BaseViewHolder
import com.allen.weather.ui.viewdata.WeatherDailyViewData
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherDailyViewHolder(
    itemView: View,
    private val onItemClicked: (view: View, viewData: WeatherDailyViewData) -> Unit
) : BaseViewHolder<WeatherDailyViewData>(itemView, onItemClicked) {

    override fun bind(item: WeatherDailyViewData) {
        super.bind(item)
        itemView.icon_v.text = item.icon
        itemView.time_v.text = item.time.weatherTimeToDailyFormat()
        itemView.temperature_v.text = item.temperature
    }
}

class WeatherDailyViewBinder(
    private val onItemClicked: (view: View, viewData: WeatherDailyViewData) -> Unit
) : BaseViewBinder<WeatherDailyViewData, WeatherDailyViewHolder>() {

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): WeatherDailyViewHolder {
        val view: View = inflater.inflate(R.layout.weather_item, parent, false)
        return WeatherDailyViewHolder(view, onItemClicked)
    }

}
