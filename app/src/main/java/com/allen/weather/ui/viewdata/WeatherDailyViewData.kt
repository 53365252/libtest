package com.allen.weather.ui.viewdata

import com.allen.weather.ui.common.recyclerview.BaseListItem

data class WeatherDailyViewData (
    override val id: String,
    val time:Long,
    val summary:String,
    val icon:String,
    val temperature:String
): BaseListItem
