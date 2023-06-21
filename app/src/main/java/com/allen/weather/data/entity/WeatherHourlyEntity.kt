package com.allen.weather.data.entity

data class WeatherHourlyEntity(
    val time:Long,
    val summary:String,
    val icon:String,
    val temperature:Double
)
