package com.allen.weather.data.entity

data class WeatherSummaryEntity(
    val timezone: String,
    val daily: WeatherDailySummaryEntity,
    val hourly: WeatherHourlySummaryEntity
)
