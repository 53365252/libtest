package com.allen.weather.data.entity

data class WeatherHourlySummaryEntity(
    val summary:String,
    val icon:String,
    val data:List<WeatherHourlyEntity>
){
    companion object{
        val EMPTY = WeatherHourlySummaryEntity(
            "",
            "",
            data = emptyList()
        )
    }
}
