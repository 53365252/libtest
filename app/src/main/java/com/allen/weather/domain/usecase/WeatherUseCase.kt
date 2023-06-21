package com.allen.weather.domain.usecase

import com.allen.weather.data.entity.WeatherDailySummaryEntity
import com.allen.weather.data.entity.WeatherHourlySummaryEntity
import com.allen.weather.domain.contract.WeatherContract
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherUseCase @Inject constructor(
    private val weatherContract: WeatherContract
) {
    fun getDailyWeather(location: String): Single<WeatherDailySummaryEntity> = weatherContract.getDailyWeather(location)

    fun getHourlyWeather(location: String, time:Long): Single<WeatherHourlySummaryEntity> = weatherContract.getHourlyWeather(location,time)
}