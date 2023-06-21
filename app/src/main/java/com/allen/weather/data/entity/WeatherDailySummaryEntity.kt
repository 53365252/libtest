package com.allen.weather.data.entity

data class WeatherDailySummaryEntity(
    val data:List<WeatherDailyEntity>
){
    companion object{
        val EMPTY = WeatherDailySummaryEntity(
            data = emptyList()
        )
    }
}
