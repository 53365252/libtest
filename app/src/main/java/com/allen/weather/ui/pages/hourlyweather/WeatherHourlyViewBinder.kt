package com.allen.weather.ui.pages.hourlyweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allen.weather.R
import com.allen.weather.extension.weatherTimeBeforeNow
import com.allen.weather.extension.weatherTimeToHourlyFormat
import com.allen.weather.ui.common.recyclerview.BaseViewBinder
import com.allen.weather.ui.common.recyclerview.BaseViewHolder
import com.allen.weather.ui.viewdata.WeatherHourlyViewData
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherHourlyViewHolder(
        itemView: View
) : BaseViewHolder<WeatherHourlyViewData>(itemView) {

    override fun bind(item: WeatherHourlyViewData) {
        super.bind(item)
        itemView.icon_v.text = item.icon
        itemView.time_v.text = item.time.weatherTimeToHourlyFormat()
        itemView.temperature_v.text = item.temperature

        itemView.setBackgroundColor(itemView.resources.getColor(
                if (item.time.weatherTimeBeforeNow()) R.color.light_orange else R.color.dark_orange,
                null))
    }
}

class WeatherHourlyViewBinder(
) : BaseViewBinder<WeatherHourlyViewData, WeatherHourlyViewHolder>() {

    override fun createViewHolder(
            inflater: LayoutInflater,
            parent: ViewGroup
    ): WeatherHourlyViewHolder {
        val view: View = inflater.inflate(R.layout.weather_item, parent, false)
        return WeatherHourlyViewHolder(view)
    }

}
