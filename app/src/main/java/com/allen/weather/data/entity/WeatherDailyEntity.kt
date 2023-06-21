package com.allen.weather.data.entity

data class WeatherDailyEntity(
    val time:Long,
    val summary:String,
    val icon:String,
    val temperatureLow:Double,
    val temperatureHigh:Double
)
