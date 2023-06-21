package com.allen.weather.domain.contract

import com.allen.weather.data.entity.WeatherDailySummaryEntity
import com.allen.weather.data.entity.WeatherHourlySummaryEntity
import io.reactivex.Single

interface WeatherContract {
    fun getDailyWeather(location: String): Single<WeatherDailySummaryEntity>

    fun getHourlyWeather(location: String, time: Long): Single<WeatherHourlySummaryEntity>
}