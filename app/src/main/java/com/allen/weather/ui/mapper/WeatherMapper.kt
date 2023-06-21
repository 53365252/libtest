package com.allen.weather.ui.mapper

import com.allen.weather.data.entity.WeatherDailySummaryEntity
import com.allen.weather.data.entity.WeatherHourlySummaryEntity
import com.allen.weather.ui.viewdata.WeatherDailyViewData
import com.allen.weather.ui.viewdata.WeatherHourlyViewData
import javax.inject.Inject

class WeatherMapper @Inject constructor(
) {

    fun toWeatherDailyViewDataList(dailyWeather: WeatherDailySummaryEntity): List<WeatherDailyViewData> =
            dailyWeather.data.map {
                WeatherDailyViewData(
                        id = it.time.toString(),
                        time = it.time,
                        summary = it.summary,
                        icon = it.icon,
                        temperature = String.format("%s - %s", it.temperatureLow, it.temperatureHigh)
                )
            }

    fun toWeatherHourlyViewDataList(hourlyWeather: WeatherHourlySummaryEntity): List<WeatherHourlyViewData> =
            hourlyWeather.data.map {
                WeatherHourlyViewData(
                        id = it.time.toString(),
                        time = it.time,
                        summary = it.summary,
                        icon = it.icon,
                        temperature = it.temperature.toString(),
                )
            }
}
