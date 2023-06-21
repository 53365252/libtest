package com.allen.weather.data.provider

import com.allen.weather.extension.isHttpUnauthorized
import com.allen.weather.data.api.WeatherApi
import com.allen.weather.data.entity.WeatherDailySummaryEntity
import com.allen.weather.data.entity.WeatherHourlySummaryEntity
import com.allen.weather.domain.contract.WeatherContract
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherProvider @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherContract {
    override fun getDailyWeather(location: String): Single<WeatherDailySummaryEntity>   =
    weatherApi.getWeather(location, WeatherApi.EXCLUDE_DAILY).map {
        it.daily
    }.onErrorReturn {
        if (it.isHttpUnauthorized()) {
            WeatherDailySummaryEntity.EMPTY
        } else {
            throw it
        }
    }

    override fun getHourlyWeather(location: String, time:Long): Single<WeatherHourlySummaryEntity> =
        weatherApi.getWeather(String.format("%s,%s", location, time), WeatherApi.EXCLUDE_HOURLY).map {
        it.hourly
    }.onErrorReturn {
        if (it.isHttpUnauthorized()) {
            WeatherHourlySummaryEntity.EMPTY
        } else {
            throw it
        }
    }


}